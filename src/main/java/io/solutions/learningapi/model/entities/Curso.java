package io.solutions.learningapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
    Author: Silas Santos Leite
    Date created: 2022-12-28
    Description: Entidade onde serão manipulados os dados referente aos cursos. Os campos que essa
    classe deverá ter serão: titulo do curso, descrição do curso, categoria, imagem do icone (URL), imagem banner (URL),
    quem cadastrou esse curso. Na classe entidade do Usuario será feito uma alteração para poder mostrar todos
    os cursos que o Usuario cadastrou.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cursoId;

    @Column(nullable = false)
    private String tituloCurso;

    @Column(nullable = false, columnDefinition =
    "LONGTEXT")
    private String descricaoCurso;

    @Column(nullable = false)
    private String cursoIconImg;

    @Column(nullable = false)
    private String cursoBannerImg;

    /*
        @ManyToOne: Significa Mmuitos Cursos para um Usuario
        @JoinColumn: Na tabela curso haverá a coluna usuario_id, mapeado pela classe Usuario
    */
    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "curso_categoria", joinColumns = @JoinColumn(name = "curso_id"))
    @Column(name = "categoria_id")
    private List<String> categorias;

    /*
        O atributo aulas deve ser instânciado.
        Um curso para muitas aulas.
    */
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Aula> aulas = new ArrayList<>();
}
