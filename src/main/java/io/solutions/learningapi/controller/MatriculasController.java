package io.solutions.learningapi.controller;

import io.solutions.learningapi.controller.docs.MatriculasControllerDocs;
import io.solutions.learningapi.exceptions.AreaProibidaException;
import io.solutions.learningapi.model.dto.MatriculaDTO;
import io.solutions.learningapi.model.dto.MatriculasDTO;
import io.solutions.learningapi.model.dto.ProfileDTO;
import io.solutions.learningapi.model.services.MatriculasService;
import io.solutions.learningapi.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class MatriculasController implements MatriculasControllerDocs {

    @Autowired
    private MatriculasService service;

    @Override
    @PostMapping("/matriculas/curso/{id}")
    public ResponseEntity<Optional<MatriculaDTO>> createMatricula(MatriculaDTO matricula, @PathVariable("id") Long idCurso, HttpServletRequest request) {
        String token = TokenUtils.wrapperToken(request);

        return ResponseEntity.ok(service.save(matricula, idCurso, token));
    }

    @Override
    @PostMapping("/matriculas/{id}/curso/{cursoId}/exercicio/{exercicioId}/resposta/{resposta}")
    public ResponseEntity<Optional<MatriculaDTO>> updateMatricula(@PathVariable("id") Long id, @PathVariable("cursoId") Long idCurso, @PathVariable("exercicioId") Long idExercicio, @PathVariable("resposta") String resposta, @RequestBody MatriculaDTO dto,
                                                                  HttpServletRequest request) throws Exception {
        String token = TokenUtils.wrapperToken(request);

        return ResponseEntity.ok(service.atualiza(id, idCurso, idExercicio, resposta, dto, token));
    }

    @Override
    @GetMapping("/matriculas")
    public ResponseEntity<List<MatriculasDTO>> listMatricula(HttpServletRequest request) {
        String token = TokenUtils.wrapperToken(request);

        return ResponseEntity.ok(service.consultaTodos(token));
    }

    @Override
    @GetMapping("/matriculas/usuario/curso/{cursoId}")
    public ResponseEntity<List<ProfileDTO>> listMatriculaByCursoId(@PathVariable("cursoId") Long cursoId) {
        return ResponseEntity.ok(service.consultarUsuariosMatriculados(cursoId));
    }

    @Override
    @DeleteMapping("/matriculas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatricula(@PathVariable("id") Long id, HttpServletRequest request) {
        String token = TokenUtils.wrapperToken(request);

        service.delete(id, token);
    }

    @Override
    @GetMapping("/matriculas/{id}")
    public ResponseEntity<Optional<MatriculaDTO>> consultaMatricula(@PathVariable("id") Long id, HttpServletRequest request) throws AreaProibidaException, AreaProibidaException {
        String token = TokenUtils.wrapperToken(request);

        return ResponseEntity.ok(service.consultaPorId(id, token));
    }
}
