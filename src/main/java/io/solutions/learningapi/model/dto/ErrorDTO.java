package io.solutions.learningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-23
    Description: Data transfer Object usada para apresentar os erros na documentação da API
 */
@Data
@AllArgsConstructor
public class ErrorDTO {
    private String field;
    private String message;
}
