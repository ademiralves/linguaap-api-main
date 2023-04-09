package io.solutions.learningapi.model.services.implementation;

import io.solutions.learningapi.exceptions.AreaProibidaException;
import io.solutions.learningapi.model.dto.LoginDTO;
import io.solutions.learningapi.model.dto.ProfileDTO;
import io.solutions.learningapi.model.dto.SignUpDTO;
import io.solutions.learningapi.model.dto.TokenDTO;
import io.solutions.learningapi.model.entities.Usuario;
import io.solutions.learningapi.model.mapper.ProfileMapper;
import io.solutions.learningapi.model.mapper.SignUpMapper;
import io.solutions.learningapi.model.repositories.UsuariosRepository;
import io.solutions.learningapi.model.services.UsuariosService;
import io.solutions.learningapi.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-23
    Description: Implementação os métodos da interface UsuariosServices. O que está sendo feito aqui é
    algo magico chamado de Inversão de Dependência. Nesta classe está sendo implementado o CRUD e as
    regras de negócios que o dominio do negócio possui.
*/

@Service
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private SignUpMapper signUpMapper = SignUpMapper.INSTANCE;

    @Autowired
    private ProfileMapper profileMapper = ProfileMapper.INSTANCE;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;


    // INSERT ou CREATE
    @Override
    public SignUpDTO cadastrar(SignUpDTO usuarioDto) {
        Usuario usuario = signUpMapper.toModel(usuarioDto);

        String pwd = usuario.getPassword();
        usuario.setPassword(new BCryptPasswordEncoder().encode(pwd));

        Usuario usuarioSalvo = usuariosRepository.save(usuario);

        return signUpMapper.toDTO(usuarioSalvo);
    }

    // SELECT ou READ
    @Override
    public TokenDTO logar(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken loginData = loginDTO.convert();
        Authentication auth = authenticationManager.authenticate(loginData);
        String token = tokenService.getToken(auth);

        return new TokenDTO(token, "Bearer");
    }


    // SELECT ou READ
    @Override
    public ProfileDTO perfil(String token) {
        Long userId = tokenService.getUserId(token);
        Usuario usuarioEncontrado = usuariosRepository.getReferenceById(userId);
        return profileMapper.toDTO(usuarioEncontrado);
    }


    // UPDATE
    @Override
    public Optional<ProfileDTO> atualizarPerfil(String token, SignUpDTO usuarioDto) {
        Long userId = tokenService.getUserId(token);

        Usuario usuario = signUpMapper.toModel(usuarioDto);
        usuario.setUsuarioId(userId);
        Usuario usuarioSalvo = usuariosRepository.save(usuario);
        ProfileDTO dto = profileMapper.toDTO(usuarioSalvo);

        return Optional.of(dto);
    }


    // READ ou SELECT
    @Override
    public ProfileDTO consultarUsuarioPeloId(Long id, String token) throws AreaProibidaException {
        Long userId = tokenService.getUserId(token);

        Usuario usuarioEncontrado = usuariosRepository.getReferenceById(userId);

        if (usuarioEncontrado.getPerfis().contains("ADMIN")) {
            Usuario usuarioConsultado = usuariosRepository.getReferenceById(id);

            return profileMapper.toDTO(usuarioConsultado);
        }

        throw new AreaProibidaException(usuarioEncontrado.getCpf());
    }

    // SELECT ou READ
    @Override
    public List<ProfileDTO> consultarUsuarios(String token) throws AreaProibidaException {
        Long userId = tokenService.getUserId(token);

        Usuario usuarioEncontrado = usuariosRepository.getReferenceById(userId);

        if (usuarioEncontrado.getPerfis().contains("ADMIN")) {

            return usuariosRepository.findAll()
                    .stream()
                    .map(profileMapper::toDTO)
                    .collect(Collectors.toList());
        }
        throw new AreaProibidaException(usuarioEncontrado.getCpf());
    }

    // UPDATE
    @Override
    public Optional<ProfileDTO> atualizarPerfilDeOutroUsuario(Long id, String token, SignUpDTO usuarioDto) throws AreaProibidaException {
        Long userId = tokenService.getUserId(token);

        Usuario usuarioEncontrado = usuariosRepository.getReferenceById(userId);

        if (usuarioEncontrado.getPerfis().contains("ADMIN")) {
            Usuario usuarioParaAtualizar = signUpMapper.toModel(usuarioDto);
            usuarioParaAtualizar.setUsuarioId(id);

            Usuario usuarioSalvo = usuariosRepository.save(usuarioParaAtualizar);

            return Optional.of(profileMapper.toDTO(usuarioSalvo));
        }

        throw new AreaProibidaException(usuarioEncontrado.getCpf());
    }

    // DELETE
    @Override
    public void deletarUsuario(Long id, String token) throws AreaProibidaException {
        Long userId = tokenService.getUserId(token);

        Usuario usuarioEncontrado = usuariosRepository.getReferenceById(userId);

        if (usuarioEncontrado.getPerfis().contains("ADMIN")) {
            usuariosRepository.deleteById(id);
        } else {
            throw new AreaProibidaException(usuarioEncontrado.getCpf());
        }
    }
}
