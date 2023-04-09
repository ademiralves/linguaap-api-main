package io.solutions.learningapi.model.services;

import io.solutions.learningapi.exceptions.AreaProibidaException;
import io.solutions.learningapi.model.dto.LoginDTO;
import io.solutions.learningapi.model.dto.ProfileDTO;
import io.solutions.learningapi.model.dto.SignUpDTO;
import io.solutions.learningapi.model.dto.TokenDTO;

import java.util.List;
import java.util.Optional;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-23
    Description: programação orientado a interface (interface-based programming). Especificando os métodos
    necessarios para o usuario logar, se cadastrar, para visualizar e autualizar o seu perfil. O admin poderá
    visualizar todos os usuarios cadastrados, atualizar os dados de um usuario e excluir um usuario da lista.
*/


public interface UsuariosService {

    SignUpDTO cadastrar(SignUpDTO usuarioDto);
    TokenDTO logar(LoginDTO loginDTO);
    ProfileDTO perfil(String token);
    Optional<ProfileDTO> atualizarPerfil(String token, SignUpDTO usuarioDto);
    ProfileDTO consultarUsuarioPeloId(Long id, String token) throws AreaProibidaException;
    List<ProfileDTO> consultarUsuarios(String token) throws AreaProibidaException;
    Optional<ProfileDTO> atualizarPerfilDeOutroUsuario(Long id, String token, SignUpDTO usuarioDto) throws AreaProibidaException;
    void deletarUsuario(Long id, String token) throws AreaProibidaException;
}
