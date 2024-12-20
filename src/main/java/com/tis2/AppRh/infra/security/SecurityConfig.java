package com.tis2.AppRh.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/processo_seletivo/**").permitAll()
                .requestMatchers("/notificacoes/**").permitAll()
                .requestMatchers("/user/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/indicadores/gerar_porcentagem_user").permitAll()
                .requestMatchers(HttpMethod.GET,"/vagas_criadas/indice/vagas-por-categoria").permitAll()
                .requestMatchers(HttpMethod.POST,"/candidato/candidatar").hasAuthority("ROLE_USER")
                .requestMatchers("/candidato/{id}/curriculo").permitAll()
                .requestMatchers(HttpMethod.GET, "/notificacoes//contarNaoLidas").permitAll()
                .requestMatchers(HttpMethod.PUT, "/notificacoes/marcarLida/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/entrevistas/agendar").hasRole("PROFISSIONAL_RH")
                .requestMatchers(HttpMethod.POST, "/gerente/solicitar-vaga").hasAuthority("ROLE_GERENTE")
                .requestMatchers(HttpMethod.PUT, "/gerente/{id}/reprovar").hasAuthority("ROLE_GERENTE")
                .requestMatchers(HttpMethod.PUT, "/gerente/{id}/aprovar").hasAuthority("ROLE_GERENTE")
                .requestMatchers(HttpMethod.DELETE, "/gerente/{id}").hasAuthority("ROLE_GERENTE")
                .requestMatchers(HttpMethod.POST, "/rh/criar-vaga").hasRole("PROFISSIONAL_RH")
                .requestMatchers(HttpMethod.POST, "/vagas_criadas/**").hasAnyAuthority("ROLE_GERENTE", "PROFISSIONAL_RH") 
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions().disable())
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .cors();  // Adicionando CORS aqui
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")  
                    .allowedOrigins("http://127.0.0.1:5500")  // Origem permitida
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  
                    .allowedHeaders("*")  
                    .allowCredentials(true);  
        }
    }
}
