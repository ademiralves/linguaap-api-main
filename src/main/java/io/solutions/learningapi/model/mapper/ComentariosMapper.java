package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.ComentariosDTO;
import io.solutions.learningapi.model.entities.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ComentariosMapper {
    ComentariosMapper INSTANCE = Mappers.getMapper(ComentariosMapper.class);

    Comentario toModel(ComentariosDTO dto);
    ComentariosDTO toDTO(Comentario model);
}
