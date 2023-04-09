package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.ExercicioDTO;
import io.solutions.learningapi.model.entities.Exercicio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-09T10:41:39-0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ExercicioMapperImpl implements ExercicioMapper {

    @Override
    public Exercicio toModel(ExercicioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Exercicio exercicio = new Exercicio();

        exercicio.setTitulo( dto.getTitulo() );
        exercicio.setEnunciado( dto.getEnunciado() );
        List<String> list = dto.getAfirmativas();
        if ( list != null ) {
            exercicio.setAfirmativas( new ArrayList<String>( list ) );
        }
        exercicio.setResposta( dto.getResposta() );

        return exercicio;
    }

    @Override
    public ExercicioDTO toDto(Exercicio model) {
        if ( model == null ) {
            return null;
        }

        ExercicioDTO exercicioDTO = new ExercicioDTO();

        exercicioDTO.setTitulo( model.getTitulo() );
        exercicioDTO.setEnunciado( model.getEnunciado() );
        List<String> list = model.getAfirmativas();
        if ( list != null ) {
            exercicioDTO.setAfirmativas( new ArrayList<String>( list ) );
        }
        exercicioDTO.setResposta( model.getResposta() );

        return exercicioDTO;
    }
}
