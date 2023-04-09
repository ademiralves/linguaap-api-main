package io.solutions.learningapi.controller.docs;


import io.solutions.learningapi.exceptions.AreaProibidaException;
import io.solutions.learningapi.model.dto.ErrorDTO;
import io.solutions.learningapi.model.dto.MatriculaDTO;
import io.solutions.learningapi.model.dto.MatriculasDTO;
import io.solutions.learningapi.model.dto.ProfileDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value = "/api/v1/matriculas",  description = "Operações relacionadas as Matriculas")
public interface MatriculasControllerDocs {

    @ApiOperation(value = "Cadastrar Matricula", nickname = "createMatricula", notes = "", response = MatriculaDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Matriculas", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matricula Cadastrada!", response = MatriculaDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrada") })
    @PostMapping
    public ResponseEntity<Optional<MatriculaDTO>> createMatricula(@Valid @RequestBody MatriculaDTO matricula, @PathVariable Long idCurso, HttpServletRequest request) throws Exception;

    @ApiOperation(value = "Atualizar Matricula", nickname = "updateMatricula", notes = "", response = MatriculaDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Matriculas", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matricula Atualizada!", response = MatriculaDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrada") })
    @PostMapping
    public ResponseEntity<Optional<MatriculaDTO>> updateMatricula(@PathVariable Long id, @PathVariable Long idCurso,  @PathVariable("exercicioId") Long idExercicio, @PathVariable("resposta") String resposta, @RequestBody MatriculaDTO dto, HttpServletRequest request) throws Exception;


    @ApiOperation(value = "Listar Matriculas do Aluno", nickname = "listMatricula", notes = "", response = MatriculaDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Matriculas", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matriculas Listados com sucesso", response = MatriculaDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado") })
    @GetMapping
    public ResponseEntity<List<MatriculasDTO>> listMatricula(HttpServletRequest request);

    @ApiOperation(value = "Lista Usuarios Matriculados em um curso", nickname = "listUsuariosMatriculado", notes = "", response = MatriculaDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Matriculas", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matriculas Listados com sucesso", response = MatriculaDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado") })
    @GetMapping
    public ResponseEntity<List<ProfileDTO>> listMatriculaByCursoId(Long cursoId);

    @ApiOperation(value = "Deletar Matricula do Aluno", nickname = "deleteMatricula", notes = "", response = MatriculaDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Matriculas", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matricula deletada com sucesso", response = MatriculaDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado") })
    @DeleteMapping
    public void deleteMatricula(@PathVariable("id") Long id, HttpServletRequest request);

    @ApiOperation(value = "Consultar Matricula do Aluno", nickname = "consultaMatricula", notes = "", response = MatriculaDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Matriculas", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matricula consultada com sucesso", response = MatriculaDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado") })
    @GetMapping
    public ResponseEntity<Optional<MatriculaDTO>> consultaMatricula(@PathVariable("id") Long id, HttpServletRequest request) throws AreaProibidaException;
}
