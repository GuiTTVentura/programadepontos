package com.guilherme.programadepontos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Pontos {

    @Id
    @GeneratedValue
    private Long id;
    private Integer valorBasePontos;
    private Double valorCompra;

    public Pontos() {
    }

    public Integer calcularPontos() {
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

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }
}
