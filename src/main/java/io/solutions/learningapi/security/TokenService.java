package io.solutions.learningapi.security;

/*
    Author: Silas Santos Leite
    Date Created: 2022-12-23
    Description: Classe responsavel por construir o token usando os milisegundo para expiração e o segredo que
    o token deve ter definido no application.properties
*/

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.solutions.learningapi.model.entities.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    /*
        @Value: busca a string que está no documento application.properties e atribui à variavel
    */

    @Value("${security.config.EXPIRATION}")
    private String expiration;

    @Value("${security.config.key}")
    private String secret;

    /*
        Método responsavel por construir o token usando a autenticação passada por parâmetro, no caso desse
        projeto a autenticação passada é o login e a senha
    */

    public String getToken(Authentication authentication) {
        Usuario logged = (Usuario) authentication.getPrincipal();
        Date today = new Date();
        Date expDate = new Date(today.getTime() + Long.parseLong(expiration));

        // No token vai o nome API Linguapp, o id do usuario, quando esse token foi gerado, a sua expiração; e
        // assinatura definida é HMAC com Sha256
        return Jwts.builder().setIssuer("API linguaap").setSubject(logged.getUsuarioId().toString())
                .setIssuedAt(today).setExpiration(expDate).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    // verifica se o token é valido usando o segredo que está na application.properties
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    // Método que retorna os dados do usuario logado (id) usando o token passado por parâmetro
    public Long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
