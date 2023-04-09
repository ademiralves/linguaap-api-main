package io.solutions.learningapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
    Author: Silas S Leite
    Date created: 2023-01-02
    Description: Entidade para manipular os dados relacionados à tabela Aula.
*/

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aulaId;

    @Column(nullable = false)
    private String tituloAula;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String descricaoAula;

    @Column(nullable = false)
    private String aulaVideoUrl;

    // Muitas aulas para um curso
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "aula", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Exercicio> exercicios = new ArrayList<>();
}
