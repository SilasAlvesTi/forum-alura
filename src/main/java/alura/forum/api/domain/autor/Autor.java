package alura.forum.api.domain.autor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    @Column(name = "autor")
    private String nome;

    public Autor(DadosAutor dados) {
        this.nome = dados.nome();
    }
}
