package io.solutions.learningapi.model.services;

import io.solutions.learningapi.model.dto.AulaDTO;
import io.solutions.learningapi.model.dto.AulasDTO;

import java.util.List;

public interface AulasService {

    AulaDTO salvarAula(AulaDTO dto, Long cursoId);
    AulaDTO atualizarAula(AulaDTO dto, Long cursoId, Long id);
    List<AulasDTO> listarAulas(Long cursoId);
    void deleteAula(Long id);
    AulasDTO retornaPeloId(Long id);
}
