package io.solutions.learningapi.controller.docs;

import io.solutions.learningapi.model.dto.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Api(value = "/api/v1/aulas",  description = "Operações relacionadas as Aulas")
public interface AulasControllerDocs {

    @ApiOperation(value = "Cadastrar uma Aula", nickname = "saveAula", notes = "", response = AulaDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Aula", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aula Cadastrado no sistema", response = AulaDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object")})
    @PostMapping("/curso/{cursoId}")
    public ResponseEntity<AulaDTO> saveAula(@RequestBody @Valid AulaDTO dto, @PathVariable("cursoId") Long cursoId);

    @ApiOperation(value = "Atualizar aula", nickname = "updateAula", notes = "", response = AulasDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Aula", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aula atualizado com sucesso!", response = AulasDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 404, message = "Aula não encontrada") })
    @PostMapping("/{id}/curso/{cursoId}")
    public ResponseEntity<AulaDTO> updateAula(@RequestBody @Valid AulaDTO dto, @PathVariable("id") Long id, @PathVariable("cursoId") Long cursoId);

    @ApiOperation(value = "Listar aulas cadastradas em um determinado curso", nickname = "listAulas", notes = "", response = AulasDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Aula", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aulas encontrado com sucesso!", response = AulasDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrados") })
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<AulasDTO>> listAulas(@PathVariable("cursoId") Long cursoId);

    @ApiOperation(value = "Deletar Aula", nickname = "deletarAula", notes = "", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Aula", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aula Deletado!", response = HttpStatusCodeException.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Aula não encontrada") })
    @DeleteMapping("/{id}")
    public void deletarAula(@PathVariable("id") Long id) throws Exception;

    @ApiOperation(value = "Consultar Aula", nickname = "consultarAula", notes = "", response = AulasDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Aula", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aula Consultados!", response = AulasDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Aula não encontrada") })
    @GetMapping("/{id}")
    public ResponseEntity<AulasDTO> consultarAula(@PathVariable("id") Long id) throws Exception;
}
