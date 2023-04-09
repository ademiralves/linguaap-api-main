package io.solutions.learningapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
    Author: Silas Santos Leite
    Date Created: 2022-12-23
    Description: Define uma exceção (customiza um exceção). Nesse caso, quando o usuario logado não estiver
    permitido a acessar um determinado recurso.

    @ResponseStatus: anotação que possibilita retorna um status http na resposta quando cair nessa exceção,
    nesse caso, caso o usuario não estiver permitido a acessar determinado recurso será http status 403 (forbidden)
*/
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AreaProibidaException extends Exception {

    public AreaProibidaException(String cpf) {
        // o cpf do cretino deve ser exposto (risos)
        super(String.format("O usuario de cpf %s está tentando acessar uma area probibida para ele.", cpf));
    }
}
