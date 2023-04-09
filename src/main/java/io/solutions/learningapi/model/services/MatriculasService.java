package io.solutions.learningapi.model.services;

import io.solutions.learningapi.exceptions.AreaProibidaException;
import io.solutions.learningapi.model.dto.MatriculaDTO;
import io.solutions.learningapi.model.dto.MatriculasDTO;
import io.solutions.learningapi.model.dto.ProfileDTO;

import java.util.List;
import java.util.Optional;

public interface MatriculasService {
    List<MatriculasDTO> consultaTodos(String token);
    Optional<MatriculaDTO> consultaPorId(Long id, String token) throws AreaProibidaException;
    Optional<MatriculaDTO> save(MatriculaDTO matricula, Long idCurso, String token);
    void delete (Long id, String token);
    Optional<MatriculaDTO> atualiza(Long id, Long idCurso, Long idExercicios, String resposta, MatriculaDTO dto, String token) throws AreaProibidaException;
    List<ProfileDTO> consultarUsuariosMatriculados(Long cursoId);
}
