package io.solutions.learningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentariosDTO {

    private Long comentarioId;
    private String comentario;
    private String username;
    private String nome;
    private String sobrenome;
}
