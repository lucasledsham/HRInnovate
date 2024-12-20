package com.tis2.AppRh.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tis2.AppRh.entities.Candidato;
import com.tis2.AppRh.entities.Gerente;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    @Query("SELECT COUNT(c) FROM Candidato c")
    Integer countTotalCandidaturas();

    @Query("SELECT COUNT(c) FROM Candidato c WHERE c.status = ?1")
    Integer countCandidaturasByStatus(int status);

    Optional<Gerente> findByEmail(String email);

    Candidato findByUserId(String userId);
}

