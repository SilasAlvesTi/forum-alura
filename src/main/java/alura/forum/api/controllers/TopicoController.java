package alura.forum.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import alura.forum.api.domain.topico.DadosDetalhamentoTopico;
import alura.forum.api.domain.topico.DadosPostagemTopico;
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
    public ResponseEntity<DadosDetalhamentoTopico> postarTopico(@RequestBody @Valid DadosPostagemTopico dados, UriComponentsBuilder uriBuilder) {
        Topico topicoDuplicado = repository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());
        if (topicoDuplicado != null) {
            throw new TopicoDuplicadoException("Já existe um tópico com o mesmo título e mensagem.");
        }

        Topico topico = new Topico(dados);
        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }
    
}
