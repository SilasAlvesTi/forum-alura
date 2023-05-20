package alura.forum.api.domain.topico;

import alura.forum.api.domain.autor.Autor;
import alura.forum.api.domain.curso.Curso;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
    @NotNull
    Long id,
    String titulo, 
    String mensagem, 
    Autor autor, 
    Curso curso

) {

}
