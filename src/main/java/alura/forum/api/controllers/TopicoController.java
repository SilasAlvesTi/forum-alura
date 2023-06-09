package alura.forum.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import alura.forum.api.domain.topico.DadosAtualizacaoTopico;
import alura.forum.api.domain.topico.DadosDetalhamentoTopico;
import alura.forum.api.domain.topico.DadosListagemTopico;
import alura.forum.api.domain.topico.DadosPostagemTopico;
import alura.forum.api.domain.topico.Status;
import alura.forum.api.domain.topico.Topico;
import alura.forum.api.domain.topico.TopicoRepository;
import alura.forum.api.infra.exception.TopicoDuplicadoException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> postar(@RequestBody @Valid DadosPostagemTopico dados, UriComponentsBuilder uriBuilder) {
        topicoTemTituloEMensagemRepetidos(dados.titulo(), dados.mensagem());

        Topico topico = new Topico(dados);
        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10, sort = {"curso"}) Pageable paginacao) {
        var page = repository.findAllByStatus(Status.PUBLICADO, paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id) {
        Topico topico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        topicoTemTituloEMensagemRepetidos(dados.titulo(), dados.mensagem());

        Topico topico = repository.getReferenceById(id);
        topico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Topico topico = repository.getReferenceById(id);
        topico.arquivar();

        return ResponseEntity.noContent().build();
    }

    private void topicoTemTituloEMensagemRepetidos(String titulo, String mensagem){
        Topico topicoDuplicado = repository.findByTituloAndMensagem(titulo, mensagem);
        if (topicoDuplicado != null) {
            throw new TopicoDuplicadoException("Já existe um tópico com o mesmo título e mensagem.");
        }
    }
    
}
