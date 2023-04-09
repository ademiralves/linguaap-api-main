package io.solutions.learningapi.utils;

import javax.servlet.http.HttpServletRequest;

/*
 Author: Silas Santos Leite
 Date created: 2022-12-23
 Description: Boilerplate que pega o conteúdo do cabeçalho Authorization e retorna o token
 que está lá, esse token é usado para descobrir o id do usuario logado.
*/
public class TokenUtils {
    public static String wrapperToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
            return null;
        }
        String token = header.substring(7, header.length());

        return token;
    }
}
