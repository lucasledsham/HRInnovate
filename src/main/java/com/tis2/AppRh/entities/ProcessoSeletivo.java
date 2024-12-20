package com.tis2.AppRh.entities;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tis2.AppRh.entities.enums.StatusProcesso;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "processo_seletivo")
public class ProcessoSeletivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "vaga_id", nullable = false)
    private VagasCriadas vaga;

    private int numMaximoCandidatos;

    private Integer status;

    @OneToMany(mappedBy = "processoSeletivo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Candidato> candidatos;

    public ProcessoSeletivo() {
    }

    public ProcessoSeletivo(VagasCriadas vaga) {
        this.vaga = vaga;
        this.numMaximoCandidatos = vaga.getVagaSolicitada().getQntd_vagas() * 3;
        this.status = StatusProcesso.EM_ABERTO.getCode();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vaga getVaga() {
        return this.vaga;
    }

    public void setVaga(VagasCriadas vaga) {
        this.vaga = vaga;
    }

    public int getNumMaximoCandidatos() {
        return this.numMaximoCandidatos;
    }

    public void setNumMaximoCandidatos(int numMaximoCandidatos) {
        this.numMaximoCandidatos = numMaximoCandidatos;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Candidato> getCandidatos() {
        return this.candidatos;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }


}
