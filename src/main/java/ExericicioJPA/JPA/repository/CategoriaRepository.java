package ExericicioJPA.JPA.repository;

import ExericicioJPA.JPA.model.Categoria;
import ExericicioJPA.JPA.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
