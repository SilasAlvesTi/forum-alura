package alura.forum.api.domain.topico;

import java.time.LocalDateTime;

import alura.forum.api.domain.autor.Autor;

public record DadosDetalhamentoTopico(
    Long id,
    String titulo,
    String mensagem,
    LocalDateTime dataCriacao,
    Status status,
    Autor autor,
    String curso

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