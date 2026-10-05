package com.guilherme.programadepontos.service;

import com.guilherme.programadepontos.model.Cliente;
import com.guilherme.programadepontos.model.Pontos;
import com.guilherme.programadepontos.repository.ClienteRepository;
import com.guilherme.programadepontos.repository.PontosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PontosRepository pontosRepository;

    public ClienteService(ClienteRepository clienteRepository, PontosRepository pontosRepository) {
        this.clienteRepository = clienteRepository;
        this.pontosRepository = pontosRepository;
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }

        String cpf = apenasNumeros(cliente.getCpf());
        String telefone = cliente.getTelefone() == null ? null : apenasNumeros(cliente.getTelefone());

        if (clienteRepository.findByCpf(cpf).isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF.");
        }
        if (telefone != null && !telefone.isEmpty() && clienteRepository.findByTelefone(telefone).isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este telefone.");
        }

        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);

        Integer pontosIniciais = pontosRepository.findById(1L)
                .map(Pontos::getPontosIniciaisCadastro)
                .orElse(0);
        cliente.setSaldoPontos(pontosIniciais == null ? 0 : pontosIniciais);

        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(apenasNumeros(cpf));
    }

    public Optional<Cliente> buscarPorTelefone(String telefone) {
        return clienteRepository.findByTelefone(apenasNumeros(telefone));
    }

    public List<Cliente> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    private String apenasNumeros(String texto) {
    if (texto == null) {
        return null;
    }
    return texto.replaceAll("[^0-9]", "");
    }
}
