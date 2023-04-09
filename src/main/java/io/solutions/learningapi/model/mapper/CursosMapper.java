package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.CursosDTO;
import io.solutions.learningapi.model.entities.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
    Author: Silas Santos Leite
    Date Created: 2022-12-28
    Description: Converte o Data Transfer Object em Entidade, e vice-versa.
*/
@Mapper(componentModel = "spring")
public interface CursosMapper {

    CursosMapper INSTANCE = Mappers.getMapper(CursosMapper.class);

    Curso toModel(CursosDTO dto);
    CursosDTO toDto(Curso model);
}
