package br.edu.infnet.hanielapi.config;

import br.edu.infnet.hanielapi.model.Acao;
import br.edu.infnet.hanielapi.model.Empresa;
import br.edu.infnet.hanielapi.service.AcaoService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;

@Component
@Order(1)
public class AcaoLoader implements ApplicationRunner {

    private final AcaoService acaoService;

    public AcaoLoader(AcaoService acaoService) {
        this.acaoService = acaoService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(">>> [AcaoLoader] Iniciando carga de dados de Ações...");

        try (BufferedReader reader = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:acoes.txt")))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");

                Empresa empresa = new Empresa(dados[4], dados[5], dados[6]);

                Acao acao = new Acao(
                        null,                       // O ID será gerado pelo Service.
                        dados[1],                   // nome do ativo
                        Integer.parseInt(dados[2]), // quantidade
                        new BigDecimal(dados[3]),   // precoMedio
                        dados[0],                   // ticker
                        empresa                     // objeto Empresa já associado
                );

                acaoService.salvar(acao);
            }
        }
        System.out.println(">>> [AcaoLoader] Carga de Ações finalizada.");
    }
}