package io.solutions.learningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
    Author: Silas Santos Leite
    Date Created:: 2022-12-23
    Description: Esse Data Transfer Object irá trazer retornar os dados do Token (o proprio token e o tipo
    que por padrão é Bearer).

    Classes para ser estudada: LoginDTO, todas as classes que estão no pacote security e no pacote utils
*/

@Data
@AllArgsConstructor
public class TokenDTO {
    private String token;
    private String type;
}
