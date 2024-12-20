package com.tis2.AppRh.infra.security;

import com.tis2.AppRh.entities.Gerente;
import com.tis2.AppRh.entities.Rh;
import com.tis2.AppRh.entities.User;
import com.tis2.AppRh.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            User user = userRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("User Not Found"));

            // A role é definida no token JWT durante sua criação, então verificamos a role do usuário aqui.
            String role = "ROLE_USER";  // Default role
            if (user instanceof Gerente) {
                role = "ROLE_GERENTE";  // Role de Gerente
            }
            if (user instanceof Rh) {
                role = "ROLE_PROFISSIONAL_RH";
            }
            

            // Associa a role ao usuário
            var authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}