package io.solutions.learningapi.model.services.implementation;

import io.solutions.learningapi.exceptions.AreaProibidaException;
import io.solutions.learningapi.exceptions.NaoEncontradoException;
import io.solutions.learningapi.model.dto.ExercicioDTO;
import io.solutions.learningapi.model.dto.ExerciciosDTO;
import io.solutions.learningapi.model.entities.Aula;
import io.solutions.learningapi.model.entities.Exercicio;
import io.solutions.learningapi.model.mapper.ExercicioMapper;
import io.solutions.learningapi.model.mapper.ExerciciosMapper;
import io.solutions.learningapi.model.repositories.AulasRepository;
import io.solutions.learningapi.model.repositories.ExerciciosRepository;
import io.solutions.learningapi.model.services.ExerciciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciciosServiceImpl implements ExerciciosService {

    @Autowired
    private ExerciciosRepository repository;

    @Autowired
    private AulasRepository aulasRepository;

    @Autowired
    private ExercicioMapper exercicioMapper = ExercicioMapper.INSTANCE;

    @Autowired
    private ExerciciosMapper exerciciosMapper = ExerciciosMapper.INSTANCE;

    @Override
    public List<ExerciciosDTO> consultaTodos(Long aulaId) {
        Aula aula = aulasRepository.getReferenceById(aulaId);

        return repository.findByAula(aula)
                .stream()
                .map(exerciciosMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ExercicioDTO> save(Long aulaId, ExercicioDTO exercicioDTO) {
        Aula aula = aulasRepository.getReferenceById(aulaId);

        Exercicio exercicio = exercicioMapper.toModel(exercicioDTO);

        if(aula != null) {

            exercicio.setAula(aula);
            repository.save(exercicio);
        }

        return Optional.ofNullable(exercicioMapper.toDto(exercicio));
    }

    @Override
    public void delete(Long id) {
        Exercicio exercicio = repository.getReferenceById(id);

        repository.delete(exercicio);
    }

    @Override
    public Optional<ExercicioDTO> atualiza(Long aulaId, Long exercicioId, ExercicioDTO exercicioDTO) throws NaoEncontradoException {
        Aula aula = aulasRepository.getReferenceById(aulaId);

        Exercicio exercicio = exercicioMapper.toModel(exercicioDTO);
        if(aula != null) {
            exercicio.setAula(aula);
            exercicio.setExercicioId(exercicioId);

            repository.save(exercicio);

            return Optional.ofNullable(exercicioMapper.toDto(exercicio));
        }

        throw new NaoEncontradoException(aulaId);
    }

    @Override
    public Optional<ExerciciosDTO> consultarPorId(Long id) throws AreaProibidaException {
        Optional<Exercicio> exercicio = repository.findById(id);

        return Optional.ofNullable(exerciciosMapper.toDto(exercicio.get()));
    }
}
