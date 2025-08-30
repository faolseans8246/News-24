package com.example.newsback.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityPermissions {


    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(
                        auth -> {

                            auth.requestMatchers(
                                    "/v2/api-docs",
                                    "/v3/api-docs/**",
                                    "/swagger-ui/**",
                                    "/swagger-ui.html",
                                    "/webjars/**",
                                    "/favicon.ico"
                            ).permitAll();

                            auth.requestMatchers(
                                    "/**"
                            ).permitAll();

                            auth.anyRequest().authenticated();
                        }
                );

        return http.build();
    }
}
