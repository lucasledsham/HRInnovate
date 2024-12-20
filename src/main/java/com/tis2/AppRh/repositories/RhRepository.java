package com.tis2.AppRh.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tis2.AppRh.entities.Rh;


public interface RhRepository extends JpaRepository<Rh,String> {
    Optional<Rh> findByEmail(String email);
}
