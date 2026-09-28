package ExericicioJPA.JPA.repository;

import ExericicioJPA.JPA.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
