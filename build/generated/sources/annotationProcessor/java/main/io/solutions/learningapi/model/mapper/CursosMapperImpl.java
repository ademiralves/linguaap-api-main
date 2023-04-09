package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.CursosDTO;
import io.solutions.learningapi.model.entities.Curso;
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
public class CursosMapperImpl implements CursosMapper {

    @Override
    public Curso toModel(CursosDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Curso curso = new Curso();

        curso.setCursoId( dto.getCursoId() );
        curso.setTituloCurso( dto.getTituloCurso() );
        curso.setDescricaoCurso( dto.getDescricaoCurso() );
        curso.setCursoIconImg( dto.getCursoIconImg() );
        curso.setCursoBannerImg( dto.getCursoBannerImg() );
        List<String> list = dto.getCategorias();
        if ( list != null ) {
            curso.setCategorias( new ArrayList<String>( list ) );
        }

        return curso;
    }

    @Override
    public CursosDTO toDto(Curso model) {
        if ( model == null ) {
            return null;
        }

        CursosDTO cursosDTO = new CursosDTO();

        cursosDTO.setCursoId( model.getCursoId() );
        cursosDTO.setTituloCurso( model.getTituloCurso() );
        cursosDTO.setDescricaoCurso( model.getDescricaoCurso() );
        cursosDTO.setCursoIconImg( model.getCursoIconImg() );
        cursosDTO.setCursoBannerImg( model.getCursoBannerImg() );
        List<String> list = model.getCategorias();
        if ( list != null ) {
            cursosDTO.setCategorias( new ArrayList<String>( list ) );
        }

        return cursosDTO;
    }
}
