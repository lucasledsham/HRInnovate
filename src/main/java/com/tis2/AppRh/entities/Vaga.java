package com.tis2.AppRh.entities;

import java.io.Serializable;

import com.tis2.AppRh.entities.enums.CategoriaVaga;

import jakarta.persistence.*;


@MappedSuperclass
public abstract class Vaga implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    
    private Integer categoriaVaga;
 
    public Vaga() {
    }

    public Vaga(Long id, String titulo, CategoriaVaga categoria) {
        this.id = id;
        this.titulo = titulo;
        setCategoriaVaga(categoria);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public CategoriaVaga getCategoriaVaga() {
        if (this.categoriaVaga != null) {
            return CategoriaVaga.valueOf(categoriaVaga); // Garante que o enum só será chamado se não for nulo
        }
        return null; // Se for nulo, retorna null em vez de causar erro
    }

    public void setCategoriaVaga(CategoriaVaga categoriaVaga) {
        if (categoriaVaga != null) {
        this.categoriaVaga = categoriaVaga.getCode();
        }
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Vaga other = (Vaga) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}

