package alura.forum.api.domain.topico;

import java.time.LocalDateTime;

import alura.forum.api.domain.autor.Autor;
import alura.forum.api.domain.curso.Curso;

public record DadosListagemTopico(
    String titulo,
    String mensagem,
    LocalDateTime dataCriacao,
    Status status,
    Autor autor,
    Curso curso

) {

    public DadosListagemTopico(Topico topico) {
        this(
            topico.getTitulo(),
            topico.getMensagem(),
            topico.getDataCriacao(),
            topico.getStatus(),
            topico.getAutor(),
            topico.getCurso()
        );
    }

}