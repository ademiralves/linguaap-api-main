package io.solutions.learningapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long comentarioId;

    @Column(columnDefinition = "LONGTEXT")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula;

    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;
}
