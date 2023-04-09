package io.solutions.learningapi.model.mapper;

import io.solutions.learningapi.model.dto.ProfileDTO;
import io.solutions.learningapi.model.entities.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-09T10:41:39-0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public Usuario toModel(ProfileDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setUsuarioId( dto.getUsuarioId() );
        usuario.setUsername( dto.getUsername() );
        usuario.setCpf( dto.getCpf() );
        usuario.setDtNascimento( dto.getDtNascimento() );
        usuario.setNome( dto.getNome() );
        usuario.setSobrenome( dto.getSobrenome() );
        usuario.setEmail( dto.getEmail() );
        usuario.setPassword( dto.getPassword() );
        List<String> list = dto.getPerfis();
        if ( list != null ) {
            usuario.setPerfis( new ArrayList<String>( list ) );
        }

        return usuario;
    }

    @Override
    public ProfileDTO toDTO(Usuario model) {
        if ( model == null ) {
            return null;
        }

        Long usuarioId = null;
        String username = null;
        String cpf = null;
        Date dtNascimento = null;
        String nome = null;
        String sobrenome = null;
        String email = null;
        String password = null;
        List<String> perfis = null;

        usuarioId = model.getUsuarioId();
        username = model.getUsername();
        cpf = model.getCpf();
        dtNascimento = model.getDtNascimento();
        nome = model.getNome();
        sobrenome = model.getSobrenome();
        email = model.getEmail();
        password = model.getPassword();
        List<String> list = model.getPerfis();
        if ( list != null ) {
            perfis = new ArrayList<String>( list );
        }

        ProfileDTO profileDTO = new ProfileDTO( usuarioId, username, cpf, dtNascimento, nome, sobrenome, email, password, perfis );

        return profileDTO;
    }
}
