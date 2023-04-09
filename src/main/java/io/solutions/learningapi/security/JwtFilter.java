package io.solutions.learningapi.security;

import io.solutions.learningapi.model.entities.Usuario;
import io.solutions.learningapi.model.repositories.UsuariosRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/*
    Author: Silas Santos Leite
    Date Created: 2022-12-23
    Description: Filtra o JWT que está sendo enviado nas requisições
*/


public class JwtFilter extends OncePerRequestFilter {
    private TokenService service;
    private UsuariosRepository repository;

    public JwtFilter(TokenService service, UsuariosRepository repository) {
        this.service = service;
        this.repository = repository;
    }


    /*
        verifica se o token é valido e realiza o filtro das requisições e das respostas destas requisições.
    */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);

        boolean isValid = service.isTokenValid(token);

        if (isValid) {
            authClient(token);
        }

        filterChain.doFilter(request, response);
    }

    // Autentica o usuario que está sendo logado
    private void authClient(String token) {
        Long userId = service.getUserId(token);
        Usuario usuario = repository.findById(userId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario.getPerfis(), usuario,
                null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    // Recupera o token do usaurio que está logado. Esse token estará no cabeçalho da sessão do usuario
    // o token estará, mais especificamente, no cabeçalho Authorization e começará com Bearer
    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }

    // Encontra os perfis que o usuario possui
    private List<SimpleGrantedAuthority> authorities(List<String> roles){
        return roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
