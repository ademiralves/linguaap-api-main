package io.solutions.learningapi.model.repositories;

import io.solutions.learningapi.model.entities.Aula;
import io.solutions.learningapi.model.entities.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciciosRepository extends JpaRepository<Exercicio, Long> {

    List<Exercicio> findByAula(Aula aula);
}
