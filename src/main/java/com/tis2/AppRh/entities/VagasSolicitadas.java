package com.tis2.AppRh.entities;


import java.time.Instant;

import com.tis2.AppRh.entities.enums.CategoriaVaga;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vagas_solicitadas")
public class VagasSolicitadas extends Vaga  {
   
    private String justificativa;
    private Integer qntd_vagas;
    private Instant data_criacao = Instant.now();

    @ManyToOne
    @JoinColumn(name = "gerente_id")
    private Gerente gerente;

    public VagasSolicitadas() {
    }

    public VagasSolicitadas(Long id, String titulo, CategoriaVaga categoria, String justificativa, Integer qntd_vagas, Gerente gerente) {
        super(id, titulo, categoria);
        this.justificativa = justificativa;
        this.qntd_vagas = qntd_vagas;
        this.data_criacao = Instant.now();
        this.gerente = gerente;
    }

    public String getJustificativa() {
        return this.justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Integer getQntd_vagas() {
        return this.qntd_vagas;
    }

    public void setQntd_vagas(Integer qntd_vagas) {
        this.qntd_vagas = qntd_vagas;
    }

    public Instant getData_criacao() {
        return this.data_criacao;
    }

    public void setData_criacao(Instant data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Gerente getGerente() {
        return this.gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

}
