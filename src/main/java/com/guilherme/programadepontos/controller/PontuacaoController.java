package com.guilherme.programadepontos.controller;

import com.guilherme.programadepontos.model.HistoricoDePontos;
import com.guilherme.programadepontos.service.PontuacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pontuacao")
public class PontuacaoController {

    private final PontuacaoService pontuacaoService;

    public PontuacaoController(PontuacaoService pontuacaoService) {
        this.pontuacaoService = pontuacaoService;
    }

    @PostMapping("/lancar-compra")
    public ResponseEntity<?> lancarCompra(@RequestBody LancamentoCompraRequisicao requisicao) {
        try {
            return ResponseEntity.ok(pontuacaoService.lancarCompra(
                    requisicao.clienteId(), requisicao.valorCompra()));
        } catch (IllegalArgumentException excecao) {
            return respostaDeErro(excecao);
        }
    }

    @PostMapping("/resgatar-recompensa")
    public ResponseEntity<?> resgatarRecompensa(@RequestBody ResgateRecompensaRequisicao requisicao) {
        try {
            return ResponseEntity.ok(pontuacaoService.resgatarRecompensa(
                    requisicao.clienteId(), requisicao.recompensaId()));
        } catch (IllegalArgumentException excecao) {
            return respostaDeErro(excecao);
        }
    }

    @GetMapping("/historico/{clienteId}")
    public ResponseEntity<List<HistoricoDePontos>> consultarHistoricoCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(pontuacaoService.consultarHistoricoCliente(clienteId));
    }

    private ResponseEntity<MensagemResposta> respostaDeErro(IllegalArgumentException excecao) {
        HttpStatus status = excecao.getMessage() != null && excecao.getMessage().contains("não encontrado")
                ? HttpStatus.NOT_FOUND
                : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new MensagemResposta(excecao.getMessage()));
    }

    public record LancamentoCompraRequisicao(Long clienteId, Double valorCompra) {
    }

    public record ResgateRecompensaRequisicao(Long clienteId, Long recompensaId) {
    }

    public record MensagemResposta(String mensagem) {
    }
}
