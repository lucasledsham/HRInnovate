package com.tis2.AppRh.entities;



import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tis2.AppRh.entities.enums.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;

    private Instant data_criacao = Instant.now();

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user") // A relação é mapeada pelo campo 'user' na classe Notificacao
    @JsonManagedReference("user-notificacao")
    private List<Notificacao> notificacoes;
}
