package io.solutions.learningapi.model.mapper;


import io.solutions.learningapi.model.dto.ExerciciosDTO;
import io.solutions.learningapi.model.entities.Exercicio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExerciciosMapper {
    ExerciciosMapper INSTANCE = Mappers.getMapper(ExerciciosMapper.class);

    Exercicio toModel(ExerciciosDTO dto);

    ExerciciosDTO toDto(Exercicio model);

}
