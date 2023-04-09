package io.solutions.learningapi.security;

import io.solutions.learningapi.model.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
    Author: Silas Santos Leite
    Date Created: 2022-12-23
    Description: Classe para configurar a autenticação, necessario para determinar o método de autenticação
    que a aplicação vai ter. Essa classe sobrepõem a autenticação padrão do Spring Security.

    Proximas Classes para estudar: AuthService, TokenService e UsuariosRepository
*/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
        @Configuration: diz ao spring boot que essa classe é uma classe de configuração.
        @EnableWebSecurity: essa anotação diz que o que está configurado aqui irá sobrepor o que é definido
        pelo Spring Security.
        @EnableGloblaMethodSecurity: essa anitação diz que a autenticação definida aqui será global.
        @Autowired: substitui o construtor, a construção da ApiSecurityConfig terá as classes AuthService, TokenService
        e a classe UsuariosRepository amarrada a ela.
    */

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private TokenService tokenService;


    /*
        @Override: permite sobreescrever os métodos que estão logo abaixo dessa anotação. Esses métodos vem
        da classe WebSecurityConfigurerAdapter.

        @Bean: diz que esse método será criado como objeto e estará disponivel para outras classes o usarem
        como objeto.
    */

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
        Esse método configure() vai ser responsavel por criptografar a senha do usuarios usando o BCrypt
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /*
      Esse método configure() será responsavel por definir as rotas que não vão passar pela autenticação;
      rotas onde é permitido requisições.
      Além de definir a politica de sessão e filtro do json web token.
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/v1/usuarios/signIn").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/usuarios/signUp").permitAll()
                .and().cors()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new JwtFilter(tokenService, usuariosRepository), UsernamePasswordAuthenticationFilter.class);;
    }

    /*
        Esse método configure() define as rotas, documentos, etc. Que serão ignorados pelo Spring Security
    */

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(  "/swagger-ui.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }
}
