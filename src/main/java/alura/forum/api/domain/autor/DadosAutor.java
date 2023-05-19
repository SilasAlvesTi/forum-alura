package alura.forum.api.domain.autor;

import jakarta.validation.constraints.NotBlank;

public record DadosAutor(@NotBlank String nome) {
    
}
