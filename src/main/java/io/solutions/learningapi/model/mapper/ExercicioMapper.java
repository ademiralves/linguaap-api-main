package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.ExercicioDTO;
import io.solutions.learningapi.model.entities.Exercicio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExercicioMapper {
    ExercicioMapper INSTANCE = Mappers.getMapper(ExercicioMapper.class);

    Exercicio toModel(ExercicioDTO dto);

    ExercicioDTO toDto(Exercicio model);
}
