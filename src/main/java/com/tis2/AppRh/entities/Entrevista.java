// src/main/java/com/tis2/AppRh/entities/Entrevista.java

package com.tis2.AppRh.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Entrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    private LocalDateTime dataHora;

    @OneToMany(mappedBy = "entrevista", cascade = CascadeType.ALL)
    @JsonIgnore 
    private List<Notificacao> notificacoes;

    private String local;

    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "confirmada")
    private Confirmacao confirmada = Confirmacao.PENDENTE;

    public enum Confirmacao {
        PENDENTE,
        CONFIRMADA,
        RECUSADA
    }

    public Entrevista() {
    }

    public Entrevista(LocalDateTime dataHora, String local, String observacoes, Candidato candidato) {
        this.candidato = null;
        this.dataHora = dataHora;
        this.local = local;
        this.observacoes = observacoes;
        this.confirmada = Confirmacao.PENDENTE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Confirmacao getConfirmada() {
        return confirmada;
    }

    public void setConfirmada(Confirmacao confirmada) {
        this.confirmada = confirmada;
    }
}