package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.MatriculasDTO;
import io.solutions.learningapi.model.entities.Matricula;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-09T10:41:39-0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class MatriculasMapperImpl implements MatriculasMapper {

    @Override
    public Matricula toModel(MatriculasDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Matricula matricula = new Matricula();

        matricula.setMatriculaId( dto.getMatriculaId() );
        matricula.setStatus( dto.getStatus() );

        return matricula;
    }

    @Override
    public MatriculasDTO toDto(Matricula model) {
        if ( model == null ) {
            return null;
        }

        MatriculasDTO matriculasDTO = new MatriculasDTO();

        matriculasDTO.setMatriculaId( model.getMatriculaId() );
        matriculasDTO.setStatus( model.getStatus() );

        return matriculasDTO;
    }
}
