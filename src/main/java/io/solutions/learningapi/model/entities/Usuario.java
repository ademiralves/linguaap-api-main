package io.solutions.learningapi.model.entities;

/*
    Author: Silas Santos Leite
    Date Created:: Sexta-feira, 23 de dezembro de 2022
    Description: Entidade usuário que será transformada em tabela de dados usando o ORM JPA. Essa classe possui
    os atributos: nome, sobrenome, nome do usuario, email, senha, cpf, data de nascimento, perfis que o usuario
    pode ter, matriculas realizadas e cursos cadastrados. Esta classe implementa a classe UserDetails para poder
    realizar a autenticação e autorização.

    (Proximas classes para estudar: LoginDTO, CadastroDTO, UsuariosRepository)
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/*
@Entity: define que essa entidade será mapeada afim de se tornar uma tabela de bd.
@Data: gera os modificadores de acesso (getters e setter) em tempo de compilação para cada atributo que essa classe tiver.
@AllArgsConstructor: gera um construtor com todos os atributos em tempo de compilação.
@NoArgsConstructor: gera um construtor sem parâmetros em tempo de compilação.
*/

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    /*
        @Id: define que esse atributo será o identificador da tabela
        @GeneratedValue: define que o identificador será gerado automaticamente; e
        usando GenerationType define que a estratégia usada para gerar o identificado da tabela será automatico
    */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long usuarioId; // tipo Long, maior que o Integer

    /*
        @Column: essa anotação é opcional, porém será definido que esses campos não serão nulos e serão
        unicos
    */
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private Date dtNascimento;

    /*
        columnDefinition: define o tipo da coluna e é possivel definir o tamanho do dado que essa coluna
        vai ter
    */
    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String nome;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String sobrenome;

    @Column(nullable = false, columnDefinition = "VARCHAR(150)")
    private String email;

    @Column(nullable = false)
    private String password; // mantive o nome em inglês

    /*
        @ElementCollection: Define que a coleção será do tipo "ansioso", ou seja, assim que o for
        adicionado, atualizado e deletado algum usuario; será adicionado logo em seguida o registro
        a essa coleção.
        @CollectionTable: define o nome da tabela e define que o usuario_id será a referência a
        chave estrangeira que ficará na tabela usuario_perfil.
        @Column: está sendo definido o nome da chave primaria da tabela usuario_perfil.

        Esse trecho é interessante, pois está sendo mapeado um atributo ao invez de mapear uma classe.
    */

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "perfil_id")
    private List<String> perfis;

    /*
        @OneToMany: Anotação que significa um usuario para varios cursos
        mappedBy: Diz que a tabela fraca será mapeada pela classe Usuario
        fetch: Representa a inclusão que deve ser Lazy (preguiçosa), em outras palavras, acontece depois
        de incluir o perfil do usuario.
        cascade: Quando deletar o usuario o usuario os cursos vão juntos
    */

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Curso> cursos;

    /*
        No método getAuthorities está sendo atribuido os perfil que foram incluido na tabela usuario_perfil,
        na Lista authorityList do tipo GrantedAuthority. Sem os perfis, ou se não for adicionado um usuário
        com no mínimo um perfil a autenticação não funciona.
    */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = Arrays.asList(new SimpleGrantedAuthority(perfis.stream().toString()));

        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // a conta não está expirado
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // a conta não vão bloquear
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // as credenciais não vão se expirar
    }

    @Override
    public boolean isEnabled() {
        return true; // a conta está ativada
    }
}
