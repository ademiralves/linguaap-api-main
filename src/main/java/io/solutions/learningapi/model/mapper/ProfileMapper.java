package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.ProfileDTO;
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
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    Usuario toModel(ProfileDTO dto);
    ProfileDTO toDTO(Usuario model);
}
