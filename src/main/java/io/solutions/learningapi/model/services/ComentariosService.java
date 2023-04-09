package io.solutions.learningapi.model.services;

import io.solutions.learningapi.model.dto.ComentarioDTO;
import io.solutions.learningapi.model.dto.ComentariosDTO;

import java.util.List;
import java.util.Optional;

public interface ComentariosService {

    List<ComentariosDTO> consultaTodos(Long aulaId);
    Optional<ComentarioDTO> save(ComentarioDTO comentarioDTO, Long aulaId, String token);
    void delete (Long id);
    Optional<ComentarioDTO> atualiza (Long id, Long aulaId, ComentarioDTO comentarioDTO, String token);
}
