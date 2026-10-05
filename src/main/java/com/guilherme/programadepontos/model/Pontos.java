package com.guilherme.programadepontos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pontos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer valorBasePontos;
    private Integer pontosIniciaisCadastro;

    public Pontos() {
    }

    public Integer calcularPontos(Double valorCompra) {
        if (valorBasePontos == null || valorCompra == null || valorBasePontos <= 0 || valorCompra <= 0) {
            return 0;
        }

        return (int) (valorCompra / valorBasePontos);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValorBasePontos() {
        return valorBasePontos;
    }

    public void setValorBasePontos(Integer valorBasePontos) {
        this.valorBasePontos = valorBasePontos;
    }

    public Integer getPontosIniciaisCadastro() {
        return pontosIniciaisCadastro;
    }

    public void setPontosIniciaisCadastro(Integer pontosIniciaisCadastro) {
        this.pontosIniciaisCadastro = pontosIniciaisCadastro;
    }
}
