package com.guilherme.programadepontos.controller;

import com.guilherme.programadepontos.model.Cliente;
import com.guilherme.programadepontos.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.cadastrarCliente(cliente));
        } catch (IllegalArgumentException excecao) {
            return ResponseEntity.badRequest().body(new MensagemResposta(excecao.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new MensagemResposta("Cliente não encontrado")));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<?> buscarPorCpf(@PathVariable String cpf) {
        return clienteService.buscarPorCpf(cpf)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new MensagemResposta("Cliente não encontrado")));
    }

    @GetMapping("/buscar-por-nome")
    public ResponseEntity<List<Cliente>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(clienteService.buscarPorNome(nome));
    }

    public record MensagemResposta(String mensagem) {
    }
}
