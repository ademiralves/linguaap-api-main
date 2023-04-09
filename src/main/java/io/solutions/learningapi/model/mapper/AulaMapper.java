package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.AulaDTO;
import io.solutions.learningapi.model.entities.Aula;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
    Author: Silas Santos Leite
    Date Created: 2022-12-28
    Description: Converte o Data Transfer Object em Entidade, e vice-versa.
*/
@Mapper(componentModel = "spring")
public interface AulaMapper {
    AulaMapper INSTANCE = Mappers.getMapper(AulaMapper.class);

    Aula toModel(AulaDTO dto);
    AulaDTO toDTO(Aula model);
}
