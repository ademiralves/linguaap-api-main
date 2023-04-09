package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.ComentariosDTO;
import io.solutions.learningapi.model.entities.Comentario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-09T10:41:39-0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ComentariosMapperImpl implements ComentariosMapper {

    @Override
    public Comentario toModel(ComentariosDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Comentario comentario = new Comentario();

        comentario.setComentarioId( dto.getComentarioId() );
        comentario.setComentario( dto.getComentario() );

        return comentario;
    }

    @Override
    public ComentariosDTO toDTO(Comentario model) {
        if ( model == null ) {
            return null;
        }

        ComentariosDTO comentariosDTO = new ComentariosDTO();

        comentariosDTO.setComentarioId( model.getComentarioId() );
        comentariosDTO.setComentario( model.getComentario() );

        return comentariosDTO;
    }
}
