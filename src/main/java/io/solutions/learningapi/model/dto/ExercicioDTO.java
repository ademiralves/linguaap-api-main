package io.solutions.learningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExercicioDTO {

    private String titulo;
    private String enunciado;
    private List<String> afirmativas;
    private String resposta;
}
