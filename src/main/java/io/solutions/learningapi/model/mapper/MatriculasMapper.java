package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.MatriculasDTO;
import io.solutions.learningapi.model.entities.Matricula;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MatriculasMapper {
    MatriculasMapper INSTANCE = Mappers.getMapper(MatriculasMapper.class);
    Matricula toModel(MatriculasDTO dto);
    MatriculasDTO toDto(Matricula model);
}
