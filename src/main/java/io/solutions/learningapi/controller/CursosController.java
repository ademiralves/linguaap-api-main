package io.solutions.learningapi.controller;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-23
    Description: Implementação da documentação CursosControllerDocs.
*/


import io.solutions.learningapi.controller.docs.CursosControllerDocs;
import io.solutions.learningapi.exceptions.NaoEncontradoException;
import io.solutions.learningapi.model.dto.CursoDTO;
import io.solutions.learningapi.model.dto.CursosDTO;
import io.solutions.learningapi.model.dto.TokenDTO;
import io.solutions.learningapi.model.services.CursosService;
import io.solutions.learningapi.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/cursos")
public class CursosController implements CursosControllerDocs {

    @Autowired
    private CursosService service;


    @Override
    @PostMapping
    public ResponseEntity<CursoDTO> saveCurso(@RequestBody @Valid CursoDTO dto, HttpServletRequest request) {
        String token = TokenUtils.wrapperToken(request);

        return ResponseEntity.ok(service.salvarCurso(dto, token));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> updateCurso(@RequestBody @Valid CursoDTO dto, @PathVariable("id") Long id, HttpServletRequest request)  {
        String token = TokenUtils.wrapperToken(request);

        return ResponseEntity.ok(service.atualizarCurso(dto, id, token));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CursosDTO>> listCursos(HttpServletRequest request) {
        String token = TokenUtils.wrapperToken(request);

        return ResponseEntity.ok(service.listaCursos(token));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable("id") Long id) throws Exception {
        service.deletaCurso(id);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CursosDTO> consultarCurso(@PathVariable("id") Long id) throws Exception {

        return ResponseEntity.ok(service.retornaPeloId(id));
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<CursosDTO>> listAllCursos() {
        return ResponseEntity.ok(service.todosCursos());
    }
}
