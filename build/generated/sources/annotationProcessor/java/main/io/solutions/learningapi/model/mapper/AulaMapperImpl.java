package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.AulaDTO;
import io.solutions.learningapi.model.entities.Aula;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-09T10:41:39-0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class AulaMapperImpl implements AulaMapper {

    @Override
    public Aula toModel(AulaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Aula aula = new Aula();

        aula.setTituloAula( dto.getTituloAula() );
        aula.setDescricaoAula( dto.getDescricaoAula() );
        aula.setAulaVideoUrl( dto.getAulaVideoUrl() );

        return aula;
    }

    @Override
    public AulaDTO toDTO(Aula model) {
        if ( model == null ) {
            return null;
        }

        AulaDTO aulaDTO = new AulaDTO();

        aulaDTO.setTituloAula( model.getTituloAula() );
        aulaDTO.setDescricaoAula( model.getDescricaoAula() );
        aulaDTO.setAulaVideoUrl( model.getAulaVideoUrl() );

        return aulaDTO;
    }
}
