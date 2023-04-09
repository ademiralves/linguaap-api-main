package io.solutions.learningapi.model.repositories;

import io.solutions.learningapi.model.entities.Matricula;
import io.solutions.learningapi.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculasRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findAllByUsuario(Usuario usuario);
    void deleteByUsuarioAndMatriculaId(Usuario usuario, Long id);

    @Query("SELECT u FROM Matricula m join m.usuario u WHERE m.curso.cursoId = :cursoId")
    List<Usuario> findUsuariosByCursoId(@Param("cursoId") Long cursoId);

}
