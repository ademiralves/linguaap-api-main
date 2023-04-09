package io.solutions.learningapi.model.services.implementation;

import io.solutions.learningapi.model.dto.ComentarioDTO;
import io.solutions.learningapi.model.dto.ComentariosDTO;
import io.solutions.learningapi.model.entities.Aula;
import io.solutions.learningapi.model.entities.Comentario;
import io.solutions.learningapi.model.entities.Usuario;
import io.solutions.learningapi.model.mapper.ComentarioMapper;
import io.solutions.learningapi.model.mapper.ComentariosMapper;
import io.solutions.learningapi.model.repositories.AulasRepository;
import io.solutions.learningapi.model.repositories.ComentariosRepository;
import io.solutions.learningapi.model.repositories.UsuariosRepository;
import io.solutions.learningapi.model.services.ComentariosService;
import io.solutions.learningapi.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentariosServiceImpl implements ComentariosService {

    @Autowired
    private ComentariosRepository repository;

    @Autowired
    private AulasRepository aulasRepository;

    @Autowired
    private ComentariosMapper comentariosMapper = ComentariosMapper.INSTANCE;

    @Autowired
    private ComentarioMapper comentarioMapper = ComentarioMapper.INSTANCE;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    public List<ComentariosDTO> consultaTodos(Long aulaId) {
        return repository.findAllByAula(aulaId);
    }

    @Override
    public Optional<ComentarioDTO> save(ComentarioDTO comentarioDTO, Long aulaId, String token) {
        Long userId = tokenService.getUserId(token);
        Usuario usuario = usuariosRepository.getReferenceById(userId);

        Aula aulaFounded = aulasRepository.getReferenceById(aulaId);


        Comentario comentario = comentarioMapper.toModel(comentarioDTO);

        comentario.setUsuario(usuario);
        comentario.setAula(aulaFounded);
        Comentario comentarioSalvo = repository.save(comentario);


        return Optional.ofNullable(comentarioMapper.toDTO(comentarioSalvo));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<ComentarioDTO> atualiza(Long id, Long aulaId, ComentarioDTO comentarioDTO, String token) {
        Long userId = tokenService.getUserId(token);
        Usuario usuario = usuariosRepository.getReferenceById(userId);

        Aula aulaFounded = aulasRepository.getReferenceById(aulaId);

        Comentario comentario = comentarioMapper.toModel(comentarioDTO);
        comentario.setComentarioId(id);
        comentario.setAula(aulaFounded);
        comentario.setUsuario(usuario);

        Comentario comentarioSalvo = repository.save(comentario);


        return Optional.ofNullable(comentarioMapper.toDTO(comentarioSalvo));
    }
}
