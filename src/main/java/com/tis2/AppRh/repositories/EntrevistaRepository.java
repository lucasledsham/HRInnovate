
package com.tis2.AppRh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis2.AppRh.entities.Entrevista;

@Repository
public interface EntrevistaRepository extends JpaRepository<Entrevista, Long> {

List<Entrevista> findByCandidatoId(Long candidatoId);



}