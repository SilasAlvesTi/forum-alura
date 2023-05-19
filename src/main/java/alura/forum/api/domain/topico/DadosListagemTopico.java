package alura.forum.api.domain.topico;

import java.time.LocalDateTime;

import alura.forum.api.domain.autor.Autor;

public record DadosListagemTopico(
    String titulo,
    String mensagem,
    LocalDateTime dataCriacao,
    Status status,
    Autor autor,
    String curso

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