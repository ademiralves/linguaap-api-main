package io.solutions.learningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-28
    Description: Data transfer Object usada para receber os dados enviados pelo usuario
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursosDTO {

    private Long cursoId;
    private String tituloCurso;
    private String descricaoCurso;
    private String cursoIconImg;
    private String cursoBannerImg;
    private List<String> categorias;
}
