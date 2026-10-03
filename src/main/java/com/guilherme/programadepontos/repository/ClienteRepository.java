package com.guilherme.programadepontos.repository;

import com.guilherme.programadepontos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String cpf);

    Optional<Cliente> findByTelefone(String telefone);

    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
