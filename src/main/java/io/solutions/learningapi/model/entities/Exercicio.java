package io.solutions.learningapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long exercicioId;

    private String titulo;

    private String enunciado;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "exercicios_afirmativas", joinColumns = @JoinColumn(name = "exercicio_id"))
    @Column(name = "afirmativas_id")
    private List<String> afirmativas;

    private String resposta;

    @ManyToOne
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula;
}
