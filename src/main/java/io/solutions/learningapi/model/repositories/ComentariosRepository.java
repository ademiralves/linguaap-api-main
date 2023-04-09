package io.solutions.learningapi.model.repositories;

import io.solutions.learningapi.model.dto.ComentariosDTO;
import io.solutions.learningapi.model.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentario, Long> {
    @Query("SELECT NEW io.solutions.learningapi.model.dto.ComentariosDTO(c.comentarioId, c.comentario, u.username, u.nome, u.sobrenome) FROM Comentario c INNER JOIN c.usuario u WHERE c.aula.aulaId = (:aulaId)")
    List<ComentariosDTO> findAllByAula(@Param("aulaId") Long aulaId);
}
