package io.solutions.learningapi.security;

import io.solutions.learningapi.model.entities.Usuario;
import io.solutions.learningapi.model.repositories.UsuariosRepository;
import io.solutions.learningapi.model.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-23
    Description: Classe de serviço do Token e o método escrito aqui será usado para autenticar e validar
    o usuario que será logado.

    @Service: diz ao spring boot que esta classe é uma classe de serviço onde fica as regras de negócios.
*/


@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuariosService service;

    @Autowired
    private UsuariosRepository repository;

    /*
        Sobreescreve o método loadUserByUsername() da classe UserDetailsService.
        o login passado como parâmetro pode ser o email ou o username.

        Se não for encontrado um email com o login passado, então será procurado no banco de dados
        usando o comando sql correto SELECT * FROM USUARIO WHERE USERNAME = ?
    */

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(login);

        if (usuario == null) {
            usuario = repository.findByUsername(login);
        }

        // verifica se o usuario é diferente de nulo e se possui perfis
        if (!validUser(usuario)) {
            // se o usuario for nulo e não possui perfis então é lançado a exceção UsernameNotFound
            throw new UsernameNotFoundException("Usuario sem permissão");
        }

        return usuario;
    }

    // vide a explicação do método loadByUsername()
    private boolean validUser(Usuario usuario) {
        boolean validUser = false;

        if (usuario != null && usuario.getPerfis() != null) {
            validUser = true;
        }

        return validUser;
    }
}
