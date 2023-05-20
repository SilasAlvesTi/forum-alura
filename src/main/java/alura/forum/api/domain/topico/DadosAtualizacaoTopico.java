package alura.forum.api.domain.topico;

import alura.forum.api.domain.autor.DadosAutor;
import alura.forum.api.domain.curso.DadosCurso;

public record DadosAtualizacaoTopico(
    String titulo, 
    String mensagem, 
    DadosAutor autor, 
    DadosCurso curso

) {

}
