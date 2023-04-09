package io.solutions.learningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-28
    Description: Data transfer Object usada para receber os dados enviados pelo usuario
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {

    @NotNull
    private String tituloCurso;
    @NotNull
    private String descricaoCurso;
    @NotNull
    private String cursoIconImg;
    @NotNull
    private String cursoBannerImg;
    @NotNull
    private List<String> categorias;
}
