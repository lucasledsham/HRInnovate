package com.tis2.AppRh.entities;

import java.time.Instant;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tis2.AppRh.entities.enums.CategoriaVaga;
import com.tis2.AppRh.entities.enums.StatusVaga;

@Entity
@Table(name = "vagas_criadas")
public class VagasCriadas extends Vaga {


    private Double salario;
    private String descricao;
    private String requisitos;
    
    private Instant dataCriacao = Instant.now();

    private Integer statusVaga = StatusVaga.AGUARDANDO_APROVACAO.getCode();

    @ManyToOne
    @JoinColumn(name = "rh_id")
     @JsonIgnoreProperties({ "senha","nome","email" })
    private Rh responsavelRh;

    @OneToOne
    @JoinColumn(name = "vaga_solicitada_id") 
    @JsonIgnoreProperties({ "justificativa", "qntd_vagas", "data_criacao", "gerente", "titulo","categoriaVaga" })
    private VagasSolicitadas vagaSolicitada;

    public VagasCriadas() {
    }

    
    public VagasCriadas(Long id, String titulo, CategoriaVaga categoria,
     String descricao, String requisitos, 
     VagasSolicitadas vagaSolicitada, Double salario, Rh responsavelRh) {
        super(id,titulo,categoria);
        this.descricao = descricao;
        this.requisitos = requisitos;
        this.salario = salario;
        this.statusVaga = StatusVaga.AGUARDANDO_APROVACAO.getCode();
        this.dataCriacao = Instant.now();
        this.responsavelRh = responsavelRh;
        this.vagaSolicitada = vagaSolicitada;
    }

    public Double getSalario() {
        return this.salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public StatusVaga getStatusVaga() {
        return StatusVaga.valueOf(this.statusVaga);
    }

    public void setStatusVaga(StatusVaga statusVaga) {
        if (statusVaga != null) {
            this.statusVaga = statusVaga.getCode();
        }
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRequisitos() {
        return this.requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public Instant getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }


    public Rh getResponsavelRh() {
        return this.responsavelRh;
    }

    public void setResponsavelRh(Rh responsavelRh) {
        this.responsavelRh = responsavelRh;
    }


    public VagasSolicitadas getVagaSolicitada() {
        return this.vagaSolicitada;
    }

    public void setVagaSolicitada(VagasSolicitadas vagaSolicitada) {
        this.vagaSolicitada = vagaSolicitada;
        
        this.setTitulo(vagaSolicitada.getTitulo());
        this.setCategoriaVaga(vagaSolicitada.getCategoriaVaga());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VagasCriadas other = (VagasCriadas) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }
}
