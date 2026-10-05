package com.guilherme.programadepontos.service;

import com.guilherme.programadepontos.model.Cliente;
import com.guilherme.programadepontos.model.HistoricoDePontos;
import com.guilherme.programadepontos.model.Pontos;
import com.guilherme.programadepontos.model.Recompensa;
import com.guilherme.programadepontos.repository.ClienteRepository;
import com.guilherme.programadepontos.repository.HistoricoDePontosRepository;
import com.guilherme.programadepontos.repository.PontosRepository;
import com.guilherme.programadepontos.repository.RecompensaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PontuacaoService {

    private final ClienteRepository clienteRepository;
    private final PontosRepository pontosRepository;
    private final RecompensaRepository recompensaRepository;
    private final HistoricoDePontosRepository historicoDePontosRepository;

    public PontuacaoService(ClienteRepository clienteRepository,
                            PontosRepository pontosRepository,
                            RecompensaRepository recompensaRepository,
                            HistoricoDePontosRepository historicoDePontosRepository) {
        this.clienteRepository = clienteRepository;
        this.pontosRepository = pontosRepository;
        this.recompensaRepository = recompensaRepository;
        this.historicoDePontosRepository = historicoDePontosRepository;
    }

    @Transactional
    public Cliente lancarCompra(Long clienteId, Double valorCompra) {
        if (valorCompra == null || !(valorCompra > 0)) {
            throw new IllegalArgumentException("O valor da compra deve ser maior que zero.");
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        Pontos pontos = pontosRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Configuração de pontos não encontrada no banco"));

        int pontosGerados = pontos.calcularPontos(valorCompra);
        if (pontosGerados > 0) {
            int saldoAtual = cliente.getSaldoPontos() == null ? 0 : cliente.getSaldoPontos();
            cliente.setSaldoPontos(saldoAtual + pontosGerados);
            cliente = clienteRepository.save(cliente);
        }

        HistoricoDePontos historico = new HistoricoDePontos();
        historico.setCliente(cliente);
        historico.setTipoTransacao("ACUMULO_COMPRA");
        historico.setPontos(pontosGerados);
        historico.setValorCompra(valorCompra);
        historico.setDataHora(LocalDateTime.now());
        historicoDePontosRepository.save(historico);

        return cliente;
    }

    @Transactional
    public HistoricoDePontos resgatarRecompensa(Long clienteId, Long recompensaId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        Recompensa recompensa = recompensaRepository.findById(recompensaId)
                .orElseThrow(() -> new IllegalArgumentException("Recompensa não encontrada"));

        if (!Boolean.TRUE.equals(recompensa.getAtivo())) {
            throw new IllegalArgumentException("A recompensa não está ativa.");
        }

        Integer pontosNecessarios = recompensa.getPontosResgate();
        int saldoAtual = cliente.getSaldoPontos() == null ? 0 : cliente.getSaldoPontos();
        if (pontosNecessarios == null || saldoAtual < pontosNecessarios) {
            throw new IllegalArgumentException("Saldo de pontos insuficiente para este resgate");
        }

        cliente.setSaldoPontos(saldoAtual - pontosNecessarios);
        clienteRepository.save(cliente);

        HistoricoDePontos historico = new HistoricoDePontos();
        historico.setCliente(cliente);
        historico.setTipoTransacao("RESGATE_RECOMPENSA");
        historico.setPontos(-pontosNecessarios);
        historico.setRecompensa(recompensa);
        historico.setDataHora(LocalDateTime.now());

        return historicoDePontosRepository.save(historico);
    }

    public List<HistoricoDePontos> consultarHistoricoCliente(Long clienteId) {
        return historicoDePontosRepository.findByClienteId(clienteId);
    }
}
