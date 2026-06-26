package com.infnet.servicosat.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI servicosOpenAPI() {
        return new OpenAPI().info(new Info()
                        .title("Clínica API - AT")
                        .description("API para gerenciamento de pacientes, médicos e consultas")
                        .version("1.0.0"));
    }
}
