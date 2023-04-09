package io.solutions.learningapi.controller;

import io.solutions.learningapi.controller.docs.UsuariosControllerDocs;
import io.solutions.learningapi.model.dto.LoginDTO;
import io.solutions.learningapi.model.dto.ProfileDTO;
import io.solutions.learningapi.model.dto.SignUpDTO;
import io.solutions.learningapi.model.dto.TokenDTO;
import io.solutions.learningapi.model.services.UsuariosService;
import io.solutions.learningapi.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-23
    Description: Implementação da documentação UsuariosController.
	@PostMapping: Método Http Post - para enviar dados para o server através de um corpo que vai na requisição.
	@GetMapping: Método Http Get - para recuperar dados do server, é possivel enviar dados através da URL
	@DeleteMapping: Método Http Delete - para deleter dados do server

	@RestController: Defini que essa classe é uma controller que faz uso do estilo arquitetural REST
	@CrossOrigin: Permiti que requisições de um determinado (ou de todos os lugares) sejam enviados para esta api. Sem essa anotação tem um erro de cross-origin request blocked
	@RequestMapping: Endpoints no qual será possivel acessar os recursos de usuarios, nesse caso através da endpoint /api/v1/usuarios
*/


@RestController
@CrossOrigin
@RequestMapping("/api/v1/usuarios")
public class UsuariosController implements UsuariosControllerDocs {

    @Autowired
    private UsuariosService service;

    @Override
    @PostMapping("/signUp")
    public ResponseEntity<SignUpDTO> signUp(SignUpDTO usuarioDto) {
        SignUpDTO responseBody = service.cadastrar(usuarioDto);

        return ResponseEntity.ok(responseBody);
    }

    @Override
    @PostMapping("/signIn")
    public ResponseEntity<TokenDTO> signIn(LoginDTO loginDTO) {
        return ResponseEntity.ok(service.logar(loginDTO));
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<ProfileDTO> me(HttpServletRequest request) {
        String token = TokenUtils.wrapperToken(request);

        return ResponseEntity.ok(service.perfil(token));
    }

    @Override
    @PostMapping("/me")
    public ResponseEntity<Optional<ProfileDTO>> updateUsuario(HttpServletRequest request,
                                                              @Valid SignUpDTO usuarioDto) throws Exception {
        String token = TokenUtils.wrapperToken(request);

        return ResponseEntity.ok(service.atualizarPerfil(token, usuarioDto));
    }

    @Override
    @PostMapping("/{id}")
    public ResponseEntity<Optional<ProfileDTO>> updateOutroUsuario(@PathVariable("id") Long id, HttpServletRequest request, SignUpDTO usuarioDto) throws Exception {
        String token = TokenUtils.wrapperToken(request);

        return ResponseEntity.ok(service.atualizarPerfilDeOutroUsuario(id,token, usuarioDto));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProfileDTO>> consultarUsuarios(HttpServletRequest request) throws Exception {
        String token = TokenUtils.wrapperToken(request);

        return ResponseEntity.ok(service.consultarUsuarios(token));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
        String token = TokenUtils.wrapperToken(request);

        service.deletarUsuario(id, token);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> consultarUsuario(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
        String token = TokenUtils.wrapperToken(request);

        return ResponseEntity.ok(service.consultarUsuarioPeloId(id, token));
    }
}
