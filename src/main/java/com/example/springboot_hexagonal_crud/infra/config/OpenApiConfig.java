package com.example.springboot_hexagonal_crud.infra.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ordersOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Orders API")
                        .description("API de gerenciamento de pedidos (CRUD) usando arquitetura hexagonal, RabbitMQ e H2 Database")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Raul Developer")
                                .email("raulhdomingues@gmail.com"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
                ).externalDocs(new ExternalDocumentation()
                        .description("Documentação completa no Swagger UI")
                        .url("http://localhost:8080/swagger-ui.html")
                );
    }
}
