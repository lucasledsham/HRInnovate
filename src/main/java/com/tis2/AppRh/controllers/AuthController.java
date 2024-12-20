package com.tis2.AppRh.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tis2.AppRh.dto.LoginRequestDTO;
import com.tis2.AppRh.dto.RegisterRequestDTO;
import com.tis2.AppRh.dto.ResponseDTO;
import com.tis2.AppRh.dto.ResponseDTOLogin;
import com.tis2.AppRh.entities.User;
import com.tis2.AppRh.entities.enums.Role;
import com.tis2.AppRh.infra.security.TokenService;
import com.tis2.AppRh.repositories.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            String redirectPage = "BuscarVagas.html"; 
            if (user.getRole() == Role.ADMIN) {
                redirectPage = "cadastro.html"; 
            }
    
            
            return ResponseEntity.ok(new ResponseDTOLogin(user.getName(), token, redirectPage));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<User> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            newUser.setRole(Role.USER);
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}