package com.tis2.AppRh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis2.AppRh.entities.Notificacao;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByUserId(String userId);

    
    Integer countByUserIdAndLidaFalse(String userId);

}
