package br.com.terracotabackend.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())  // Desabilita o CSRF
                .formLogin(login -> login.disable())  // Desabilita o login via formulário
                .httpBasic(basic -> basic.disable())  // Desabilita o HTTP Basic
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Configura o uso de JWT sem manter sessão
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/api/v1/admin").permitAll()  // Permite acesso sem autenticação
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()  // Permite login sem autenticação
                        .requestMatchers(HttpMethod.POST, "/api/v1/customer").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/craftsman").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/company").permitAll()
                        .anyRequest().authenticated())  // Requer autenticação para qualquer outra rota
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)  // Adiciona o filtro de segurança
                .cors()  // Habilita o CORS
                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
