package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.ComentarioDTO;
import io.solutions.learningapi.model.entities.Comentario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-09T10:41:39-0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ComentarioMapperImpl implements ComentarioMapper {

    @Override
    public Comentario toModel(ComentarioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Comentario comentario = new Comentario();

        comentario.setComentario( dto.getComentario() );

        return comentario;
    }

    @Override
    public ComentarioDTO toDTO(Comentario model) {
        if ( model == null ) {
            return null;
        }

        ComentarioDTO comentarioDTO = new ComentarioDTO();

        comentarioDTO.setComentario( model.getComentario() );

        return comentarioDTO;
    }
}
