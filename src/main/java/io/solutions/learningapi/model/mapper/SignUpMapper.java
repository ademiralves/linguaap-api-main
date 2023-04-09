package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.SignUpDTO;
import io.solutions.learningapi.model.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
    Author: Silas Santos Leite
    Date Created: 2022-12-23
    Description: Converte o Data Transfer Object em Entidade, e vice-versa.
*/
@Mapper(componentModel = "spring")
public interface SignUpMapper {
    // cria a instancia da classe SignUpMapper usando a Mappers do mapstruct
    SignUpMapper INSTANCE = Mappers.getMapper(SignUpMapper.class);

    // converte o data transfer object em entidade
    Usuario toModel(SignUpDTO dto);
    // converte a entidade em data transfer object
    SignUpDTO toDTO(Usuario model);
}
