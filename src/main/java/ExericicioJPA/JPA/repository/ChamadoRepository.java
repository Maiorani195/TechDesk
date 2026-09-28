package ExericicioJPA.JPA.repository;

import ExericicioJPA.JPA.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado,Long> {
}
