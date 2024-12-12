package br.com.terracotabackend.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // Cria uma configuração CORS
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*"); // Permite todas as origens
        corsConfig.addAllowedMethod("*"); // Permite todos os métodos (GET, POST, etc.)
        corsConfig.addAllowedHeader("*"); // Permite todos os cabeçalhos
        corsConfig.setAllowCredentials(false); // Se precisar de cookies ou credenciais, altere para true

        // Configura a origem URL do CORS
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Aplica as configurações para todas as rotas

        return new CorsFilter(source); // Retorna o filtro de CORS
    }
}
