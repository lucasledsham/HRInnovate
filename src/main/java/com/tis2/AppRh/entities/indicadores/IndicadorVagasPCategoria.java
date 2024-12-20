package com.tis2.AppRh.entities.indicadores;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "indicadores_vagas_por_categoria")
public class IndicadorVagasPCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long totalVagas; // Total de vagas no período
    private Instant dataConsulta; // Data da consulta
    private double percentualDesenvolvimento;
    private double percentualAdministrativo;
    private double percentualFinanceiro;

    // Construtores
    public IndicadorVagasPCategoria() {}

    public IndicadorVagasPCategoria(long totalVagas, Instant dataConsulta, double percentualDesenvolvimento, 
                                     double percentualAdministrativo, double percentualFinanceiro) {
        this.totalVagas = totalVagas;
        this.dataConsulta = dataConsulta;
        this.percentualDesenvolvimento = percentualDesenvolvimento;
        this.percentualAdministrativo = percentualAdministrativo;
        this.percentualFinanceiro = percentualFinanceiro;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTotalVagas() {
        return totalVagas;
    }

    public void setTotalVagas(long totalVagas) {
        this.totalVagas = totalVagas;
    }

    public Instant getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Instant dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public double getPercentualDesenvolvimento() {
        return percentualDesenvolvimento;
    }

    public void setPercentualDesenvolvimento(double percentualDesenvolvimento) {
        this.percentualDesenvolvimento = percentualDesenvolvimento;
    }

    public double getPercentualAdministrativo() {
        return percentualAdministrativo;
    }

    public void setPercentualAdministrativo(double percentualAdministrativo) {
        this.percentualAdministrativo = percentualAdministrativo;
    }

    public double getPercentualFinanceiro() {
        return percentualFinanceiro;
    }

    public void setPercentualFinanceiro(double percentualFinanceiro) {
        this.percentualFinanceiro = percentualFinanceiro;
    }

    @Override
    public String toString() {
        return "IndicadorVagasPCategoria{" +
                "id=" + id +
                ", totalVagas=" + totalVagas +
                ", dataConsulta=" + dataConsulta +
                ", percentualDesenvolvimento=" + percentualDesenvolvimento +
                ", percentualAdministrativo=" + percentualAdministrativo +
                ", percentualFinanceiro=" + percentualFinanceiro +
                '}';
    }
}
