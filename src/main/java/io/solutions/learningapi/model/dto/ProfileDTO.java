package io.solutions.learningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

/*
    Author: Silas Santos Leite
    Date Created:: 2022-12-23
    Description: Essa classe (Data Transfer Object) será responsavel por trazer as informações do usuario que
    está no banco de dados, dessa forma será possivel atualizar e listar os usarios cadastrados.
*/


@Data
@AllArgsConstructor
public class ProfileDTO {

    private Long usuarioId;
    private String username;
    private String cpf;
    private Date dtNascimento;
    private String nome;
    private String sobrenome;
    private String email;
    private String password;
    private List<String> perfis;
}
