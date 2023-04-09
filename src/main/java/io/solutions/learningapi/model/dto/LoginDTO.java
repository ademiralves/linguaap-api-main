package io.solutions.learningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/*
    Author: Silas Santos Leite
    Date Created: 2022-12-23
    Description: Essa Data Transfer Object receberá os dados de autenticação, o login poderá ser o email ou
    o username; e também receberá a senha. Essa classe possui o método convert() para converter a classe LoginDTO
    em UsernamePasswordAuthenticationToken

    Classes para ser estudada: TokenDTO, todas as classes que estão no pacote security e no pacote utils
*/

@Data
@AllArgsConstructor
public class LoginDTO {
    private String login;
    private String password;

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(login, password);
    }
}
