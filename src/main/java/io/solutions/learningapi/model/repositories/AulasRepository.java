package io.solutions.learningapi.model.repositories;

import io.solutions.learningapi.model.entities.Aula;
import io.solutions.learningapi.model.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    Author: Silas S Leite
    Date Create: 2023-01-02
    Description: Interface que extende a classe JPA, no qual traz todos os métodos necessario para realizar
    o CRUD.
*/

@Repository
public interface AulasRepository extends JpaRepository<Aula, Long> {

    List<Aula> findAllByCurso(Curso curso);
}
