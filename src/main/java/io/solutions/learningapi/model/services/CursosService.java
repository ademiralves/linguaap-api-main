package io.solutions.learningapi.model.services;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-23
    Description: programação orientado a interface (interface-based programming). Especificando os métodos
    necessarios para salvar o curso, retornar um curso usando o id, deletar, para visualizar e atualizar o cursos.
*/

import io.solutions.learningapi.exceptions.NaoEncontradoException;
import io.solutions.learningapi.model.dto.CursoDTO;
import io.solutions.learningapi.model.dto.CursosDTO;

import java.util.List;

public interface CursosService {

    CursoDTO salvarCurso(CursoDTO dto, String token);
    CursoDTO atualizarCurso(CursoDTO dto, Long id, String token);
    List<CursosDTO> listaCursos(String token);
    void deletaCurso(Long id);
    CursosDTO retornaPeloId(Long id);
    List<CursosDTO> todosCursos();
}
