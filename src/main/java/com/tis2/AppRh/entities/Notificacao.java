package com.tis2.AppRh.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tis2.AppRh.entities.enums.TipoNotificacao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    private boolean lida;

    private TipoNotificacao tipoNotificacao;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-notificacao")
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "processo_seletivo_id")
    private ProcessoSeletivo processoSeletivo;

    @ManyToOne
    @JoinColumn(name = "entrevista_id")
    @JsonIgnore // Evita serializar o objeto completo
    private Entrevista entrevista;

    public Notificacao() {
    }

    public Notificacao(Long id, String mensagem, boolean lida, User user, ProcessoSeletivo processoSeletivo) {
        this.id = id;
        this.mensagem = mensagem;
        this.lida = lida;
        this.user = user;
        this.processoSeletivo = processoSeletivo;
        this.tipoNotificacao = TipoNotificacao.TIPO_APROVACAO;
    }

    public Notificacao(Long id, String mensagem, boolean lida, User user, Entrevista entrevista) {
        this.id = id;
        this.mensagem = mensagem;
        this.lida = lida;
        this.user = user;
        this.entrevista = entrevista;
        this.tipoNotificacao = TipoNotificacao.TIPO_ENTREVISTA;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isLida() {
        return this.lida;
    }

    public boolean getLida() {
        return this.lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public ProcessoSeletivo getProcessoSeletivo() {
        return this.processoSeletivo;
    }

    public void setProcessoSeletivo(ProcessoSeletivo processoSeletivo) {
        this.processoSeletivo = processoSeletivo;
    }

    public Entrevista getEntrevista() {
        return this.entrevista;
    }

    public void setEntrevista(Entrevista entrevista) {
        this.entrevista = entrevista;
    }

    public TipoNotificacao getTipoNotificacao() {
        return this.tipoNotificacao;
    }

    public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    @JsonProperty("entrevistaId") // Adiciona apenas o ID da Entrevista no JSON
    public Long getEntrevistaId() {
        return this.entrevista != null ? this.entrevista.getId() : null;
    }

}
