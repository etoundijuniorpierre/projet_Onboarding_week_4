package com.example.checkpoint.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable) // Désactiver CSRF pour les API RESTful (si pas de gestion de token CSRF)
                    .cors(cors -> cors.configurationSource((CorsConfigurationSource) corsConfigurationSource())) // Activer CORS
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                            .anyRequest().authenticated()
                    )
                    .httpBasic(withDefaults());

            return http.build();
        }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Permet les credentials (cookies, headers d'autorisation)
        config.setAllowedOriginPatterns(Arrays.asList("http://localhost:*", "http://localhost:8082*")); // nom du domaine frontend exact
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Les headers autorisés
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Les méthodes HTTP autorisées
        source.registerCorsConfiguration("/**", config); // Applique cette configuration à tous les chemins
        return source;
    }

        @Bean
        public UserDetailsService users() {
            UserDetails user = User.withUsername("admin")
                    .password(passwordEncoder().encode("password123"))
                    .roles("ADMIN")
                    .build();

            return new InMemoryUserDetailsManager(user);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

