package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.CursoDTO;
import io.solutions.learningapi.model.entities.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
    Author: Silas Santos Leite
    Date Created: 2022-12-28
    Description: Converte o Data Transfer Object em Entidade, e vice-versa.
*/
@Mapper(componentModel = "spring")
public interface CursoMapper {

    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);

    Curso toModel(CursoDTO dto);
    CursoDTO toDto(Curso model);
}
