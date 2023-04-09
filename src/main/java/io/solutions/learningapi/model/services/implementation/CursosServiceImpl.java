package io.solutions.learningapi.model.services.implementation;

import io.solutions.learningapi.exceptions.NaoEncontradoException;
import io.solutions.learningapi.model.dto.CursoDTO;
import io.solutions.learningapi.model.dto.CursosDTO;
import io.solutions.learningapi.model.entities.Curso;
import io.solutions.learningapi.model.entities.Usuario;
import io.solutions.learningapi.model.mapper.CursoMapper;
import io.solutions.learningapi.model.mapper.CursosMapper;
import io.solutions.learningapi.model.repositories.CursosRepository;
import io.solutions.learningapi.model.repositories.UsuariosRepository;
import io.solutions.learningapi.model.services.CursosService;
import io.solutions.learningapi.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursosServiceImpl implements CursosService {

    @Autowired
    private CursosRepository repository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CursosMapper cursosMapper = CursosMapper.INSTANCE;

    @Autowired
    private CursoMapper cursoMapper = CursoMapper.INSTANCE;


    @Override
    @Transactional
    public CursoDTO salvarCurso(CursoDTO dto, String token) {
        Long usuarioId = tokenService.getUserId(token);
        Usuario usuarioEncontrado = usuariosRepository.getReferenceById(usuarioId);

        Curso curso = cursoMapper.toModel(dto);
        curso.setUsuario(usuarioEncontrado);
        Curso cursoSalvo = repository.save(curso);

        return cursoMapper.toDto(cursoSalvo);
    }

    @Override
    @Transactional
    public CursoDTO atualizarCurso(CursoDTO dto, Long id, String token) {
        Long usuarioId = tokenService.getUserId(token);
        Usuario usuarioEncontrado = usuariosRepository.getReferenceById(usuarioId);

        Curso curso = cursoMapper.toModel(dto);

        curso.setCursoId(id);
        curso.setUsuario(usuarioEncontrado);
        Curso cursoSalvo = repository.save(curso);

        return cursoMapper.toDto(cursoSalvo);
    }

    @Override
    public List<CursosDTO> listaCursos(String token) {
        Long usuarioId = tokenService.getUserId(token);
        Usuario usuarioEncontrado = usuariosRepository.getReferenceById(usuarioId);

        return repository.findAllByUsuario(usuarioEncontrado)
                .stream()
                .map(cursosMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletaCurso(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CursosDTO retornaPeloId(Long id) {
        Curso cursoEncontrado = repository.getReferenceById(id);

        return cursosMapper.toDto(cursoEncontrado);
    }

    @Override
    public List<CursosDTO> todosCursos() {
        return repository.findAll()
                .stream()
                .map(cursosMapper::toDto)
                .collect(Collectors.toList());
    }
}
