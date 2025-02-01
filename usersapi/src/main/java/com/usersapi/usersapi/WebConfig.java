package com.usersapi.usersapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://192.168.1.80");  // Permite tu origen //comprobar si hay que añadir la ip de la página web o de la del usuario
        corsConfiguration.addAllowedMethod("*");  // Permite todos los métodos
        corsConfiguration.addAllowedHeader("*");  // Permite todos los headers
        corsConfiguration.setAllowCredentials(true);  // Permite credenciales (cookies, autenticación)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/v1/**", corsConfiguration);  // Aplica a todas las rutas bajo /api/v1/
        return new CorsFilter(source);
    }
}
