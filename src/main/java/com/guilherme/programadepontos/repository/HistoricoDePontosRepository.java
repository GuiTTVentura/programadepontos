package com.guilherme.programadepontos.repository;

import com.guilherme.programadepontos.model.HistoricoDePontos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoricoDePontosRepository extends JpaRepository<HistoricoDePontos, Long> {

    List<HistoricoDePontos> findByClienteId(Long clienteId);
}
