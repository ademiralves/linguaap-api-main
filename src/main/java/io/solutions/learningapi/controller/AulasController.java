package io.solutions.learningapi.controller;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-23
    Description: Implementação da documentação CursosControllerDocs.
*/

import io.solutions.learningapi.controller.docs.AulasControllerDocs;
import io.solutions.learningapi.model.dto.AulaDTO;
import io.solutions.learningapi.model.dto.AulasDTO;
import io.solutions.learningapi.model.dto.CursosDTO;
import io.solutions.learningapi.model.services.AulasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/aulas")
public class AulasController implements AulasControllerDocs {

    @Autowired
    private AulasService service;

    @Override
    @PostMapping("/curso/{cursoId}")
    public ResponseEntity<AulaDTO> saveAula(@RequestBody @Valid AulaDTO dto, @PathVariable("cursoId") Long cursoId) {
        return ResponseEntity.ok(service.salvarAula(dto, cursoId));
    }

    @Override
    @PutMapping("{id}/curso/{cursoId}")
    public ResponseEntity<AulaDTO> updateAula(@RequestBody @Valid AulaDTO dto, @PathVariable("id") Long id, @PathVariable("cursoId") Long cursoId) {
        return ResponseEntity.ok(service.atualizarAula(dto, cursoId, id));
    }

    @Override
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<AulasDTO>> listAulas(Long cursoId) {
        return ResponseEntity.ok(service.listarAulas(cursoId));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAula(Long id) throws Exception {
        service.deleteAula(id);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AulasDTO> consultarAula(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(service.retornaPeloId(id));
    }
}
