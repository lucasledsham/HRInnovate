package com.tis2.AppRh.dto;

public class EntrevistaDTO {

    private Long id;
    private Long candidatoId;
    private String dataHora;
    private String local;
    private String observacoes;
    private String confirmacao;

    // Construtores
    public EntrevistaDTO() {}

    public EntrevistaDTO(Long id, Long candidatoId, String dataHora, String local, String observacoes, String confirmacao) {
        this.id = id;
        this.candidatoId = candidatoId;
        this.dataHora = dataHora;
        this.local = local;
        this.observacoes = observacoes;
        this.confirmacao = confirmacao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCandidatoId() {
        return candidatoId;
    }

    public void setCandidatoId(Long candidatoId) {
        this.candidatoId = candidatoId;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
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

    public String getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(String confirmacao) {
        this.confirmacao = confirmacao;
    }
}
