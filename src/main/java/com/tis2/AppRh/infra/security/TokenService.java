package com.tis2.AppRh.infra.security;

import com.tis2.AppRh.entities.Gerente;
import com.tis2.AppRh.entities.Rh;
import com.tis2.AppRh.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try {
            // Define a role do usuário no token
            String role = "ROLE_USER";  // Role padrão
            if (user instanceof Gerente) {  // Caso o usuário seja um gerente
                role = "ROLE_GERENTE";
            }
            if (user instanceof Rh) {
                role = "PROFISSIONAL_RH";
            }

            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Adiciona a role ao token
            String token = JWT.create()
                    .withIssuer("AppRh")
                    .withSubject(user.getEmail())
                    .withClaim("role", role)  // Adiciona a role ao token
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error while authenticating");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("AppRh")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}