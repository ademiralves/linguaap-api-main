package io.solutions.learningapi.model.services.implementation;

import io.solutions.learningapi.exceptions.AreaProibidaException;
import io.solutions.learningapi.model.dto.MatriculaDTO;
import io.solutions.learningapi.model.dto.MatriculasDTO;
import io.solutions.learningapi.model.dto.ProfileDTO;
import io.solutions.learningapi.model.entities.Curso;
import io.solutions.learningapi.model.entities.Exercicio;
import io.solutions.learningapi.model.entities.Matricula;
import io.solutions.learningapi.model.entities.Usuario;
import io.solutions.learningapi.model.mapper.MatriculaMapper;
import io.solutions.learningapi.model.mapper.MatriculasMapper;
import io.solutions.learningapi.model.mapper.ProfileMapper;
import io.solutions.learningapi.model.repositories.CursosRepository;
import io.solutions.learningapi.model.repositories.ExerciciosRepository;
import io.solutions.learningapi.model.repositories.MatriculasRepository;
import io.solutions.learningapi.model.repositories.UsuariosRepository;
import io.solutions.learningapi.model.services.MatriculasService;
import io.solutions.learningapi.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatriculasServiceImpl implements MatriculasService {

    @Autowired
    private MatriculasRepository repository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private MatriculasMapper matriculasMapper = MatriculasMapper.INSTANCE;

    @Autowired
    private MatriculaMapper matriculaMapper = MatriculaMapper.INSTANCE;

    @Autowired
    private ProfileMapper usuariosMapper = ProfileMapper.INSTANCE;

    @Autowired
    private ExerciciosRepository exercicioRepository;

    @Autowired
    private CursosRepository cursosRepositories;

    @Autowired
    private TokenService tokenService;

    @Override
    public List<MatriculasDTO> consultaTodos(String token) {
        Long userId = tokenService.getUserId(token);

        Usuario usuario = usuariosRepository.getReferenceById(userId);

        return repository.findAllByUsuario(usuario)
                .stream()
                .map(matriculasMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MatriculaDTO> consultaPorId(Long id, String token) throws AreaProibidaException {
        Long userId = tokenService.getUserId(token);

        Usuario usuario = usuariosRepository.getReferenceById(userId);

        if (usuario.getPerfis().contains("ALUNO") || usuario.getPerfis().contains("ADMIN") || usuario.getPerfis().contains("INSTRUTOR")) {
            Matricula matricula = repository.getReferenceById(id);
            MatriculaDTO matriculaDTO = matriculaMapper.toDto(matricula);

            return Optional.of(matriculaDTO);
        }

        throw new AreaProibidaException(usuario.getCpf());
    }

    @Transactional
    @Override
    public Optional<MatriculaDTO> save(MatriculaDTO matricula, Long idCurso, String token) {
        Long userId = tokenService.getUserId(token);

        Usuario usuario = usuariosRepository.getReferenceById(userId);
        Curso curso = cursosRepositories.getReferenceById(idCurso);

        Matricula matriculaEntity = matriculaMapper.toModel(matricula);

        matriculaEntity.setUsuario(usuario);
        matriculaEntity.setCurso(curso);
        repository.save(matriculaEntity);

        return Optional.of(matricula);
    }

    @Transactional
    @Override
    public void delete(Long id, String token) {
        Long userId = tokenService.getUserId(token);

        Usuario usuario = usuariosRepository.getReferenceById(userId);

        repository.deleteByUsuarioAndMatriculaId(usuario, id);
    }

    @Override
    public Optional<MatriculaDTO> atualiza(Long id, Long idCurso, Long idExercicios, String resposta, MatriculaDTO dto, String token) throws AreaProibidaException {
        Long userId = tokenService.getUserId(token);

        Usuario usuario = usuariosRepository.getReferenceById(userId);
        Curso curso = cursosRepositories.getReferenceById(idCurso);

        Matricula matricula = repository.getReferenceById(id);

        Exercicio exercicio = exercicioRepository.getReferenceById(idExercicios);

        if (exercicio.getResposta().equals(resposta)) {
            matricula.setUsuario(usuario);
            matricula.setCurso(curso);
            matricula.setMatriculaId(id);
            matricula.setStatus(dto.getStatus());
            return Optional.of(matriculaMapper.toDto(repository.save(matricula)));
        }

        throw new AreaProibidaException(usuario.getCpf());
    }

    @Override
    public List<ProfileDTO> consultarUsuariosMatriculados(Long cursoId) {
        return repository.findUsuariosByCursoId(cursoId)
                .stream()
                .map(usuariosMapper::toDTO)
                .collect(Collectors.toList());
    }
}
