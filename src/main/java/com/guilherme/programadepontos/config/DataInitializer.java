package com.guilherme.programadepontos.config;

import com.guilherme.programadepontos.model.Pontos;
import com.guilherme.programadepontos.model.Recompensa;
import com.guilherme.programadepontos.model.Cliente;
import com.guilherme.programadepontos.repository.ClienteRepository;
import com.guilherme.programadepontos.repository.PontosRepository;
import com.guilherme.programadepontos.repository.RecompensaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PontosRepository pontosRepository;
    private final RecompensaRepository recompensaRepository;
    private final ClienteRepository clienteRepository;

    public DataInitializer(PontosRepository pontosRepository,
                           RecompensaRepository recompensaRepository,
                           ClienteRepository clienteRepository) {
        this.pontosRepository = pontosRepository;
        this.recompensaRepository = recompensaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(String... args) {
        if (pontosRepository.count() == 0) {
            Pontos pontos = new Pontos();
            pontos.setValorBasePontos(10);
            pontos.setPontosIniciaisCadastro(0);
            pontosRepository.save(pontos);
        }

        if (recompensaRepository.count() == 0) {
            Recompensa cafeExpresso = new Recompensa();
            cafeExpresso.setNome("Café Expresso");
            cafeExpresso.setDescricao("Um café expresso.");
            cafeExpresso.setPontosResgate(20);
            cafeExpresso.setAtivo(true);
            recompensaRepository.save(cafeExpresso);

            Recompensa desconto = new Recompensa();
            desconto.setNome("Desconto de R$ 10");
            desconto.setDescricao("Desconto de R$ 10 em uma compra.");
            desconto.setPontosResgate(50);
            desconto.setAtivo(true);
            recompensaRepository.save(desconto);
        }

        if (clienteRepository.count() == 0) {
            Cliente cliente = new Cliente();
            cliente.setNome("Cliente Teste");
            cliente.setCpf("12345678900");
            cliente.setTelefone("31999998888");
            cliente.setSaldoPontos(0);
            clienteRepository.save(cliente);
        }
    }
}
