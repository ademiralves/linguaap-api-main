package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.ExerciciosDTO;
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
public class ExerciciosMapperImpl implements ExerciciosMapper {

    @Override
    public Exercicio toModel(ExerciciosDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Exercicio exercicio = new Exercicio();

        exercicio.setExercicioId( dto.getExercicioId() );
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
    public ExerciciosDTO toDto(Exercicio model) {
        if ( model == null ) {
            return null;
        }

        ExerciciosDTO exerciciosDTO = new ExerciciosDTO();

        exerciciosDTO.setExercicioId( model.getExercicioId() );
        exerciciosDTO.setTitulo( model.getTitulo() );
        exerciciosDTO.setEnunciado( model.getEnunciado() );
        List<String> list = model.getAfirmativas();
        if ( list != null ) {
            exerciciosDTO.setAfirmativas( new ArrayList<String>( list ) );
        }
        exerciciosDTO.setResposta( model.getResposta() );

        return exerciciosDTO;
    }
}
