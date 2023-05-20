package alura.forum.api.domain.topico;

import alura.forum.api.domain.autor.DadosAutor;
import alura.forum.api.domain.curso.DadosCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosPostagemTopico(
        @NotBlank
        String titulo, 
        @NotBlank
        String mensagem, 
        @NotNull
        DadosAutor autor, 
        @NotNull
        DadosCurso curso
    ) {

}
