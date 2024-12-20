package com.tis2.AppRh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tis2.AppRh.entities.Gerente;
import com.tis2.AppRh.entities.Notificacao;
import com.tis2.AppRh.entities.User;
import com.tis2.AppRh.repositories.UserRepository;
import com.tis2.AppRh.services.UserService;



@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("sucesso!");
    }

    
    @GetMapping("/notifications")
    public List<Notificacao> getUserNotifications() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getEmail();  

        user = carregarUser(email);

        if (user == null) {
            throw new RuntimeException("User não encontrado!");  
        }

        return user.getNotificacoes();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Void> confirmarEntrevista(@PathVariable Long id) {
        userService.confirmarEntrevista(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ROLE_USER')")  
    @PutMapping("/{id}/reprovar")
    public ResponseEntity<Void> reprovarVaga(@PathVariable Long id) {
        userService.recusarEntrevista(id);
        return ResponseEntity.noContent().build();
    }

    private User carregarUser(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
    

