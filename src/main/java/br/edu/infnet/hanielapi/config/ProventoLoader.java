package br.edu.infnet.hanielapi.config;

import br.edu.infnet.hanielapi.exception.AtivoNaoEncontradoException;
import br.edu.infnet.hanielapi.model.Acao;
import br.edu.infnet.hanielapi.model.Provento;
import br.edu.infnet.hanielapi.service.AcaoService;
import br.edu.infnet.hanielapi.service.ProventoService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Order(3)
public class ProventoLoader implements ApplicationRunner {

    private final ProventoService proventoService;
    private final AcaoService acaoService;

    public ProventoLoader(ProventoService proventoService, AcaoService acaoService) {
        this.proventoService = proventoService;
        this.acaoService = acaoService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("[ProventoLoader] -> Iniciando carga de dados de Proventos...");
        try (BufferedReader reader = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:proventos.txt")))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String ticker = dados[0];

                try {
                    Acao acao = acaoService.buscarPorTicker(ticker);
                    
                    Provento provento = new Provento();
                    provento.setTipo(dados[1]);
                    provento.setDataPagamento(LocalDate.parse(dados[2]));
                    provento.setValorPorAcao(new BigDecimal(dados[3]));

                    proventoService.salvar(provento, acao.getId());

                } catch (AtivoNaoEncontradoException e) {
                    System.err.println("[ProventoLoader] Erro ao carregar provento: " + e.getMessage());
                }
            }
        }
        System.out.println("[ProventoLoader] -> Carga de Proventos finalizada.");
    }
}