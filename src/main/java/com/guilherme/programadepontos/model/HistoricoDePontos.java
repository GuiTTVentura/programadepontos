package com.guilherme.programadepontos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class HistoricoDePontos {

    @Id
    @GeneratedValue
    private Long id;
    private String nomeCliente;
    private LocalDate data;
    private String recompensaResgatada;
    private Double valorCompra;
    private Integer saldoDePontos;

    public HistoricoDePontos() {
    }

    public HistoricoDePontos(Long id, String nomeCliente, LocalDate data, Double valorCompra, Integer saldoDePontos) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.data = data;
        this.valorCompra = valorCompra;
        this.saldoDePontos = saldoDePontos;
    }

    public HistoricoDePontos(Long id, String nomeCliente, LocalDate data, String recompensaResgatada, Integer saldoDePontos) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.data = data;
        this.recompensaResgatada = recompensaResgatada;
        this.saldoDePontos = saldoDePontos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getRecompensaResgatada() {
        return recompensaResgatada;
    }

    public void setRecompensaResgatada(String recompensaResgatada) {
        this.recompensaResgatada = recompensaResgatada;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Integer getSaldoDePontos() {
        return saldoDePontos;
    }

    public void setSaldoDePontos(Integer saldoDePontos) {
        this.saldoDePontos = saldoDePontos;
    }
}
