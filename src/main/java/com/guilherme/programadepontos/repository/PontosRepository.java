package com.guilherme.programadepontos.repository;

import com.guilherme.programadepontos.model.Pontos;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PontosRepository extends JpaRepository<Pontos, Long> {
}
