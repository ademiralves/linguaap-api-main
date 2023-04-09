package io.solutions.learningapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long matriculaId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;
    private String status;

    @OneToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
}
