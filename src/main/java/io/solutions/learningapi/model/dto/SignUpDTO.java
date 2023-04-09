package io.solutions.learningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/*
    Author: Silas Santos Leite
    Date Created:: 2022-12-23
    Description: Data Transfer Object é a classe responsável por receber os inputs do usuarios e posteriormente
    essa classe será convertida em entidade para então ser manipulada no banco de dados.

    Dessa forma, está sendo escondido informações de login, como por exemplo: a conta do usuário está bloqueada?
*/

@Data
@AllArgsConstructor
public class SignUpDTO {

    /*
        @NotNull: diz que o atributo não pode estar nulo.
        @CPF: diz que o atributo será um CPF de uma pessoa existente no Brasil.
        @Email: o email deve ser válido
        @Size: define o tamanho de caracteres que o dado deverá ter.
    */

    @NotNull
    private String username;

    @CPF
    @NotNull
    private String cpf;

    @NotNull
    private Date dtNascimento;

    @NotNull
    private String nome;

    @NotNull
    private String sobrenome;

    @Email(message = "Insira um email valido")
    @NotNull
    private String email;

    @Size(min = 8, message = "A senha deve ter no minimo 8 caracteres.")
    @NotNull
    private String password;

    @NotNull
    private List<String> perfis;
}
