package alura.forum.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alura.forum.api.domain.topico.DadosPostagemTopico;
import alura.forum.api.domain.topico.Topico;
import alura.forum.api.domain.topico.TopicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public void postarTopico(@RequestBody @Valid DadosPostagemTopico dados) {
        Topico topico = repository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());
        if (topico != null) {
            return;
        }
        repository.save(new Topico(dados));
    }
    
}
