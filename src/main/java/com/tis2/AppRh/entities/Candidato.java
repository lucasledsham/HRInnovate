package com.tis2.AppRh.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tis2.AppRh.entities.enums.StatusCandidato;


import jakarta.persistence.*;

@Entity
@Table(name = "candidato", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "processo_seletivo_id" }))
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;
    private String telefone;
    private String email;

    @Column(length = 1000)
    private String cartaApresentacao;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "processo_seletivo_id")
    @JsonBackReference
    private ProcessoSeletivo processoSeletivo;

    @OneToMany(mappedBy = "candidato")
    private List<Entrevista> entrevistas;

    private Integer status = StatusCandidato.EM_ANALISE.getCode();

    @Lob
    @Column(name = "curriculo", nullable = true, columnDefinition = "LONGBLOB")
    private byte[] curriculo;

    public Candidato() {
    }

    public Candidato(String nomeCompleto, String telefone, String email, String cartaApresentacao,
            User user, ProcessoSeletivo processoSeletivo) {
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
        this.email = email;
        this.cartaApresentacao = cartaApresentacao;
        this.status = StatusCandidato.EM_ANALISE.getCode();
        this.processoSeletivo = processoSeletivo;
        this.user = user;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCartaApresentacao() {
        return cartaApresentacao;
    }

    public void setCartaApresentacao(String cartaApresentacao) {
        this.cartaApresentacao = cartaApresentacao;
    }

    public ProcessoSeletivo getProcessoSeletivo() {
        return processoSeletivo;
    }

    public void setProcessoSeletivo(ProcessoSeletivo processoSeletivo) {
        this.processoSeletivo = processoSeletivo;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(StatusCandidato status) {
        if (status != null) {
            this.status = status.getCode();
        }
    }

    @JsonProperty("status")
    public StatusCandidato getStatusCandidato() {
        return StatusCandidato.valueOf(this.status);
    }

    public byte[] getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(byte[] curriculo) {
        this.curriculo = curriculo;
    }
}
