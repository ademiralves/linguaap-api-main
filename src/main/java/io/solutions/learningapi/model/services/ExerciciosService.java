package io.solutions.learningapi.model.services;

import io.solutions.learningapi.exceptions.AreaProibidaException;
import io.solutions.learningapi.exceptions.NaoEncontradoException;
import io.solutions.learningapi.model.dto.ExercicioDTO;
import io.solutions.learningapi.model.dto.ExerciciosDTO;

import java.util.List;
import java.util.Optional;

public interface ExerciciosService {

    List<ExerciciosDTO> consultaTodos(Long aulaId);
    //Optional<ExercicioDTO> consultaPorTitulo(String titulo);
    Optional<ExercicioDTO> save(Long aulaId, ExercicioDTO exercicioDTO);
    void delete (Long id);
    Optional<ExercicioDTO> atualiza(Long aulaId, Long exercicioId, ExercicioDTO exercicioDTO) throws NaoEncontradoException;
    Optional<ExerciciosDTO> consultarPorId(Long id) throws AreaProibidaException;
}
