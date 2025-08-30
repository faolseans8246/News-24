package com.example.newsback.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {

        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Newa site")
                        .description("News web application test")
                        .version("1.0.0")
                );
    }
}
