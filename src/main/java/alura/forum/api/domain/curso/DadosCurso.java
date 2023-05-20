package alura.forum.api.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosCurso(@NotBlank String nome) {
    
}
