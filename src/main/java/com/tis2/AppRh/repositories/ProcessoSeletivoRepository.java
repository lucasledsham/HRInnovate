package com.tis2.AppRh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis2.AppRh.entities.ProcessoSeletivo;
import com.tis2.AppRh.entities.enums.StatusProcesso;

@Repository
public interface ProcessoSeletivoRepository extends JpaRepository<ProcessoSeletivo,Long> {
    List<ProcessoSeletivo> findByStatus(StatusProcesso status);
}
