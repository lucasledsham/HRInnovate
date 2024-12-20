package com.tis2.AppRh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis2.AppRh.entities.indicadores.IndicadorVagasPCategoria;

@Repository
public interface IndicadorVagasPCategoriaRepository extends JpaRepository<IndicadorVagasPCategoria, Long> {
    
}
