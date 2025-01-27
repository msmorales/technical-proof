package com.mssv.tecpr.Config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi customOpenApi() {
        return GroupedOpenApi.builder()
            .group("API de Clientes y Préstamos")
            .pathsToMatch("/**") // Incluye todos los endpoints
            .build();
    }
}