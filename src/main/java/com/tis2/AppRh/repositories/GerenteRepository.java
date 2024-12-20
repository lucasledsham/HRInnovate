package com.tis2.AppRh.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tis2.AppRh.entities.Gerente;



public interface GerenteRepository extends JpaRepository<Gerente,String> {
    Optional<Gerente> findByEmail(String email);
} 