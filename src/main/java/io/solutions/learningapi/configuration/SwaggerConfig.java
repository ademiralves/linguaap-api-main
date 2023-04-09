package io.solutions.learningapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/*
    Author: Silas Santos Leite
    Date created: 2022-12
    Description: Classe que configura o Swagger / OpenAPI definindo em qual pacote estão as classes com
    as rotas que API terá.

    Proximas classes para estudar estão no pacote controller e controller>>docs
*/

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String BASE_PACKAGE = "io.solutions.learningapi.controller";
    private static final String API_TITLE = "Plataforma E-learning";
    private static final String API_DESCRIPTION = "REST API desenvolvida para o Linguaap";
    private static final String CONTACT_NAME = "Leite, S. S.; Martins, Ademir";
    private static final String CONTACT_GITHUB = "https://github.com/LeiteSS";
    private static final String CONTACT_EMAIL = "leite.silassantos@gmail.com";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInfo())
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version("0.1.0")
                .contact(new Contact(CONTACT_NAME, CONTACT_GITHUB, CONTACT_EMAIL))
                .build();
    }

    /*
        Botão autorization, no qual será possivel logar na documentação da API
    /*/
    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }
}
