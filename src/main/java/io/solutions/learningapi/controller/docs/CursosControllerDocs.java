package io.solutions.learningapi.controller.docs;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-28
    Description: Interface que documenta as rotas que a endpoint para manipular os dados do Curso terá.
*/


import io.solutions.learningapi.exceptions.NaoEncontradoException;
import io.solutions.learningapi.model.dto.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value = "/api/v1/cursos",  description = "Operações relacionadas aos Cursos")
public interface CursosControllerDocs {

    @ApiOperation(value = "Cadastrar um Curso", nickname = "saveCurso", notes = "", response = CursoDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Curso", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Curso Cadastrado no sistema", response = CursoDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object")})
    @PostMapping("/")
    public ResponseEntity<CursoDTO> saveCurso(@RequestBody @Valid CursoDTO dto, HttpServletRequest request);

    @ApiOperation(value = "Atualizar curso", nickname = "updateCurso", notes = "", response = CursoDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Curso", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cusro atualizado com sucesso!", response = CursoDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 404, message = "Curso não encontrada") })
    @PostMapping("/{id}")
    public ResponseEntity<CursoDTO> updateCurso(@RequestBody @Valid CursoDTO dto, @PathVariable("id") Long id, HttpServletRequest request);

    @ApiOperation(value = "Listar cursos cadastrados pelo instrutor", nickname = "listCursos", notes = "", response = CursosDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Curso", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Dados do usuario encontrado com sucesso!", response = CursosDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrados") })
    @GetMapping("/")
    public ResponseEntity<List<CursosDTO>> listCursos(HttpServletRequest request);

    @ApiOperation(value = "Deletar Curso", nickname = "deleteCurso", notes = "", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Curso", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Curso Deletado!", response = HttpStatusCodeException.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Curso não encontrada") })
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable("id") Long id) throws Exception;

    @ApiOperation(value = "Consultar Curso", nickname = "consultarCurso", notes = "", response = CursosDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Curso", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Curso Consultados!", response = CursosDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Curso não encontrada") })
    @GetMapping("/{id}")
    public ResponseEntity<CursosDTO> consultarCurso(@PathVariable("id") Long id) throws Exception;

    @ApiOperation(value = "Todos os Cursos Listados", nickname = "listCursos", notes = "", response = CursosDTO.class, responseContainer = "object", tags = { "Curso", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Dados do usuario encontrado com sucesso!", response = CursosDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrados") })
    @GetMapping("/all")
    public ResponseEntity<List<CursosDTO>> listAllCursos();
}
