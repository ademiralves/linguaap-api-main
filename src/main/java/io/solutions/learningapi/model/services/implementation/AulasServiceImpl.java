package io.solutions.learningapi.model.services.implementation;

import io.solutions.learningapi.model.dto.AulaDTO;
import io.solutions.learningapi.model.dto.AulasDTO;
import io.solutions.learningapi.model.entities.Aula;
import io.solutions.learningapi.model.entities.Curso;
import io.solutions.learningapi.model.mapper.AulaMapper;
import io.solutions.learningapi.model.mapper.AulasMapper;
import io.solutions.learningapi.model.repositories.AulasRepository;
import io.solutions.learningapi.model.repositories.CursosRepository;
import io.solutions.learningapi.model.services.AulasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulasServiceImpl implements AulasService {

    @Autowired
    private AulasRepository aulasRepository;

    @Autowired
    private CursosRepository cursosRepository;

    @Autowired
    private AulasMapper aulasMapper = AulasMapper.INSTANCE;

    @Autowired
    private AulaMapper aulaMapper = AulaMapper.INSTANCE;

    @Override
    public AulaDTO salvarAula(AulaDTO dto, Long cursoId) {

        Curso curso = cursosRepository.getReferenceById(cursoId);
        Aula aula = aulaMapper.toModel(dto);

        if (curso != null) {
            aula.setCurso(curso);

            aulasRepository.save(aula);
        }

        return aulaMapper.toDTO(aula);
    }

    @Override
    @Transactional
    public AulaDTO atualizarAula(AulaDTO dto, Long cursoId, Long id) {
        Curso curso = cursosRepository.getReferenceById(cursoId);
        Aula aula = aulaMapper.toModel(dto);

        if (curso != null) {

            aula.setAulaId(id);
            aula.setCurso(curso);

            aulasRepository.save(aula);
        }

        return aulaMapper.toDTO(aula);
    }

    @Override
    public List<AulasDTO> listarAulas(Long cursoId) {
        Curso curso = cursosRepository.getReferenceById(cursoId);

        return aulasRepository.findAllByCurso(curso)
                .stream()
                .map(aulasMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAula(Long id) {
        aulasRepository.deleteById(id);
    }

    @Override
    public AulasDTO retornaPeloId(Long id) {
        Aula aula = aulasRepository.getReferenceById(id);

        return aulasMapper.toDTO(aula);
    }
}
