package ExericicioJPA.JPA.repository;

import ExericicioJPA.JPA.model.Chamado;
import ExericicioJPA.JPA.model.PrioridadeChamado;
import ExericicioJPA.JPA.model.StatusChamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    @Query("SELECT c FROM Chamado c WHERE c.usuario.id = :usuarioId AND c.status = :status")
    List<Chamado> buscarPorUsuarioEStatus(@Param("usuarioId") Long usuarioId,
                                          @Param("status") StatusChamado status);

    @Query("SELECT c FROM Chamado c WHERE c.categoria.id = :categoriaId")
    List<Chamado> buscarPorCategoria(@Param("categoriaId") Long categoriaId);

    @Query("SELECT c FROM Chamado c WHERE c.prioridade IN (:prioridades) AND c.status <> :status")
    List<Chamado> buscarCriticosNaoFechados(@Param("prioridades") List<PrioridadeChamado> prioridades,
                                            @Param("status") StatusChamado status);

    @Query("SELECT c.usuario.nome, COUNT(c) FROM Chamado c " +
            "GROUP BY c.usuario.id, c.usuario.nome ORDER BY COUNT(c) DESC")
    List<Object[]> rankingUsuarios();
}