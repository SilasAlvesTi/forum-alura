package alura.forum.api.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosPostagemTopico(
        @NotBlank
        String titulo, 
        @NotBlank
        String mensagem, 
        @NotBlank
        String autor, 
        @NotBlank
        String curso
    ) {

}
