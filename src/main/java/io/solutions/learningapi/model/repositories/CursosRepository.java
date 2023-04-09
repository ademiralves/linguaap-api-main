package io.solutions.learningapi.model.repositories;

import io.solutions.learningapi.model.dto.CursoDTO;
import io.solutions.learningapi.model.entities.Curso;
import io.solutions.learningapi.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-28
    Description: Interface que traz todas as operações necessaria para realizar o CRUD; é possivel criar novos
    comandos SQL
*/
@Repository
public interface CursosRepository extends JpaRepository<Curso, Long> {
    // SELECT * FROM CURSO WHERE USUARIO = ?
    List<Curso> findAllByUsuario(Usuario usuario);
}
