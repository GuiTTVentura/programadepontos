package com.guilherme.programadepontos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Recompensa {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String descricao;
    private Integer pontosResgate;
    private Boolean ativo;

    public Recompensa() {
    }

    public Recompensa(Long id, String nome, String descricao, Integer pontosResgate, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.pontosResgate = pontosResgate;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPontosResgate() {
        return pontosResgate;
    }

    public void setPontosResgate(Integer pontosResgate) {
        this.pontosResgate = pontosResgate;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
