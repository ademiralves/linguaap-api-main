package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.MatriculaDTO;
import io.solutions.learningapi.model.entities.Matricula;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-09T10:41:39-0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class MatriculaMapperImpl implements MatriculaMapper {

    @Override
    public Matricula toModel(MatriculaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Matricula matricula = new Matricula();

        matricula.setStatus( dto.getStatus() );

        return matricula;
    }

    @Override
    public MatriculaDTO toDto(Matricula model) {
        if ( model == null ) {
            return null;
        }

        MatriculaDTO matriculaDTO = new MatriculaDTO();

        matriculaDTO.setStatus( model.getStatus() );

        return matriculaDTO;
    }
}
