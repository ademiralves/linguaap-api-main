package io.solutions.learningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    Author: Silas S Leite
    Date created: 2023-01-02
    Description: Data transfer Object para poder ter acesso ao identificador da entidade que será manipulada.
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AulasDTO {

    private Long aulaId;
    private String tituloAula;
    private String descricaoAula;
    private String aulaVideoUrl;
}
