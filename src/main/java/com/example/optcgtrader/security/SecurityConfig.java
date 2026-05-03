package com.example.optcgtrader.security;

import org.springframework.http.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                // AUTH
                .requestMatchers("/auth/**").permitAll()

                // SWAGGER
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ).permitAll()

                // CARDS PUBLICO
                .requestMatchers(HttpMethod.GET, "/api/cards/**")
                .permitAll()

                // SOLO ADMIN
                .requestMatchers(HttpMethod.POST, "/api/cards/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT, "/api/cards/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE, "/api/cards/**")
                .hasRole("ADMIN")

                // TODO LO DEMAS AUTENTICADO
                .anyRequest().authenticated()
        )
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}