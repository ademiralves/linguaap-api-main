package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.ComentarioDTO;
import io.solutions.learningapi.model.entities.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {
    ComentarioMapper INSTANCE = Mappers.getMapper(ComentarioMapper.class);

    Comentario toModel(ComentarioDTO dto);
    ComentarioDTO toDTO(Comentario model);
}
