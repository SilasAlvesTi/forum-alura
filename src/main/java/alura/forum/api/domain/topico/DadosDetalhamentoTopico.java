package alura.forum.api.domain.topico;

import java.time.LocalDateTime;

import alura.forum.api.domain.autor.Autor;
import alura.forum.api.domain.curso.Curso;

public record DadosDetalhamentoTopico(
    Long id,
    String titulo,
    String mensagem,
    LocalDateTime dataCriacao,
    Status status,
    Autor autor,
    Curso curso

) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(
            topico.getId(),
            topico.getTitulo(),
            topico.getMensagem(),
            topico.getDataCriacao(),
            topico.getStatus(),
            topico.getAutor(),
            topico.getCurso()
        );
    }

}