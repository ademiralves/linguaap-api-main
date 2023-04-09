package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.MatriculaDTO;
import io.solutions.learningapi.model.entities.Matricula;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MatriculaMapper {
    MatriculaMapper INSTANCE = Mappers.getMapper(MatriculaMapper.class);

    Matricula toModel(MatriculaDTO dto);

    MatriculaDTO toDto(Matricula model);
}
