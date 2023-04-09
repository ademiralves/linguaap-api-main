package io.solutions.learningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/*
    Author: Silas S Leite
    Date created: 2023-01-02
    Description: Data transfer Object sem o ID, pois o usuario não precisa ter acesso a esse tipo de informação.
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AulaDTO {

    @NotNull
    private String tituloAula;

    @NotNull
    private String descricaoAula;

    @NotNull
    private String aulaVideoUrl;
}
