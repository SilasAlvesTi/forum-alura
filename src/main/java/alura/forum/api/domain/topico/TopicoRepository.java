package alura.forum.api.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    
    Topico findByTituloAndMensagem(String titulo, String mensagem);

    Page<Topico> findAllByStatus(Status status, Pageable page);
}
