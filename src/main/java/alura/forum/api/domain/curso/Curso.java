package alura.forum.api.domain.curso;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    @Column(name = "curso")
    private String nome;

    public Curso(DadosCurso dados) {
        this.nome = dados.nome();
    }

    public void atualizarInformacoes(DadosCurso dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
    }
}
