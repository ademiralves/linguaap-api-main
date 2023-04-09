package io.solutions.learningapi.controller.docs;

import io.solutions.learningapi.model.dto.LoginDTO;
import io.solutions.learningapi.model.dto.ProfileDTO;
import io.solutions.learningapi.model.dto.SignUpDTO;
import io.solutions.learningapi.model.dto.TokenDTO;
import io.solutions.learningapi.model.dto.ErrorDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-23
    Description: Interface que documenta as rotas que a API terá.
	@Api: Anotação principal para definir as endpoints de operações relacionadas aos Usuarios.
	@ApiOperation: Documenta as operações da api, definindo um apelido, a classe que será a resposta, assim
	como a tag que poderá ser vista no Swagger UI
	@ApiResponses: Defini os codigos http para as respostas de sucesso, falha, etc. @ApiResponse deve ser definido o codigo, mensagem e a classe de resposta
*/


@Api(value = "/api/v1/usuarios",  description = "Operações relacionadas aos Usuarios")
public interface UsuariosControllerDocs {

    @ApiOperation(value = "Cadastrar um usuario", nickname = "signUp", notes = "", response = SignUpDTO.class, responseContainer = "object", tags = { "Usuario", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario Cadastrado no sistema", response = SignUpDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object")})
    @PostMapping("/signUp")
    public ResponseEntity<SignUpDTO> signUp(@RequestBody @Valid SignUpDTO signUpDTO);

    @ApiOperation(value = "Logar no sistema", nickname = "signIn", notes = "", response = TokenDTO.class, responseContainer = "object", tags = { "Usuario", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario Logado com sucesso!", response = TokenDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 404, message = "Usuário não encontrada") })
    @PostMapping("/signIn")
    public ResponseEntity<TokenDTO> signIn(@RequestBody @Valid LoginDTO loginDTO);

    @ApiOperation(value = "Dados do usuario logado", nickname = "me", notes = "", response = ProfileDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Usuario", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Dados do usuario encontrado com sucesso!", response = ProfileDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrados") })
    @GetMapping("/me")
    public ResponseEntity<ProfileDTO> me(HttpServletRequest request);

    @ApiOperation(value = "Atualizar Usuario", nickname = "updateUsuario", notes = "", response = ProfileDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Usuario", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario Atualizado!", response = ProfileDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrada") })
    @PostMapping
    public ResponseEntity<Optional<ProfileDTO>> updateUsuario(HttpServletRequest request, @RequestBody @Valid SignUpDTO signUpDTO) throws Exception;


    @ApiOperation(value = "Atualizar Outro Usuario", nickname = "updateOutroUsuario", notes = "", response = ProfileDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Usuario", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario Atualizado!", response = ProfileDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrada") })
    @PostMapping
    public ResponseEntity<Optional<ProfileDTO>> updateOutroUsuario(@PathVariable("id") Long id, HttpServletRequest request, @RequestBody @Valid SignUpDTO signUpDTO) throws Exception;

    @ApiOperation(value = "Consultar Usuarios", nickname = "consultarUsuarios", notes = "", response = ProfileDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Usuario", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuarios Consultados!", response = SignUpDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrada") })
    @PostMapping
    public ResponseEntity<List<ProfileDTO>> consultarUsuarios(HttpServletRequest request) throws Exception;

    @ApiOperation(value = "Deletar Usuario", nickname = "deleteUsuario", notes = "", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Usuario", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario Deletado!", response = HttpStatusCodeException.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrada") })
    @DeleteMapping
    public void deletarUsuario(@PathVariable("id")  Long id, HttpServletRequest request) throws Exception;

    @ApiOperation(value = "Consultar Usuario", nickname = "consultarUsuario", notes = "", response = ProfileDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Usuario", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario Consultados!", response = ProfileDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrada") })
    @PostMapping
    public ResponseEntity<ProfileDTO> consultarUsuario(@PathVariable("id") Long id, HttpServletRequest request) throws Exception;
}
