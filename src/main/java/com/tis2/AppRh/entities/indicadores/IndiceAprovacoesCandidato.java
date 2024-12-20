package com.tis2.AppRh.entities.indicadores;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "indice_aprovacoes_candidato")
public class IndiceAprovacoesCandidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataConsulta;

    private double indiceAprovacao;

    public IndiceAprovacoesCandidato() {
        this.dataConsulta = LocalDateTime.now(); // Define a data no momento da criação
    }

    public IndiceAprovacoesCandidato(double indiceAprovacao) {
        this.dataConsulta = LocalDateTime.now();
        this.indiceAprovacao = indiceAprovacao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public double getIndiceAprovacao() {
        return indiceAprovacao;
    }

    public void setIndiceAprovacao(double indiceAprovacao) {
        this.indiceAprovacao = indiceAprovacao;
    }
}

