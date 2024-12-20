package com.tis2.AppRh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tis2.AppRh.entities.User;

import java.time.Instant;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {


    @Query("SELECT COUNT(u) FROM User u WHERE u.data_criacao BETWEEN :dataInicio AND :dataFim")
    Integer getUsuariosCadastradosNoPeriodo(@Param("dataInicio") Instant dataInicio, @Param("dataFim") Instant dataFim);

    @Query("SELECT COUNT(u) FROM User u")
    Integer getTotalUsuarios();



    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);
}