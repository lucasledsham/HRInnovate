package com.tis2.AppRh.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tis2.AppRh.entities.VagasCriadas;
import com.tis2.AppRh.entities.enums.StatusVaga;

@Repository
public interface VagasCriadasRepository extends JpaRepository<VagasCriadas,Long> {
    @Query("SELECT v FROM VagasCriadas v WHERE v.dataCriacao BETWEEN :startDate AND :endDate")
    List<VagasCriadas> findByDataCriacaoBetween(Instant startDate, Instant endDate);

    List<VagasCriadas> findByStatusVaga(StatusVaga statusVaga);
}
