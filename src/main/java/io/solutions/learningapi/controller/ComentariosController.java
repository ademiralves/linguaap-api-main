package io.solutions.learningapi.controller;


import io.solutions.learningapi.controller.docs.ComentariosControllerDocs;
import io.solutions.learningapi.model.dto.ComentarioDTO;
import io.solutions.learningapi.model.dto.ComentariosDTO;
import io.solutions.learningapi.model.services.ComentariosService;
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
public class ComentariosController implements ComentariosControllerDocs {

    @Autowired
    private ComentariosService service;


    @Override
    @PostMapping("/comentarios/aula/{aulaId}")
    public ResponseEntity<Optional<ComentarioDTO>> comentar(@RequestBody ComentarioDTO comentario, @PathVariable("aulaId") Long aulaId, HttpServletRequest request) throws Exception {
        String token = TokenUtils.wrapperToken(request);

        return ResponseEntity.ok(service.save(comentario, aulaId, token));
    }

    @Override
    @GetMapping("/comentarios/aula/{aulaId}")
    public ResponseEntity<List<ComentariosDTO>> listComentarios(@PathVariable("aulaId") Long aulaId) {
        return ResponseEntity.ok(service.consultaTodos(aulaId));
    }

    @Override
    @DeleteMapping("/comentarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComentario(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
