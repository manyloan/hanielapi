package br.edu.infnet.hanielapi.config;

import br.edu.infnet.hanielapi.model.Acao;
import br.edu.infnet.hanielapi.model.Ativo;
import br.edu.infnet.hanielapi.model.FundoImobiliario;
import br.edu.infnet.hanielapi.service.AtivoService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;

@Component
public class DataLoader implements ApplicationRunner {

    private final AtivoService ativoService;

    public DataLoader(AtivoService ativoService) {
        this.ativoService = ativoService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("[DataLoader] -> Iniciando carga de dados do arquivo ativos.txt...");

        try (BufferedReader reader = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:ativos.txt")))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String tipo = dados[0];
                Ativo ativo = null;

                if ("ACAO".equalsIgnoreCase(tipo)) {
                    ativo = new Acao(null, dados[2], Integer.parseInt(dados[4]), new BigDecimal(dados[5]), dados[1], dados[3]);
                } else if ("FII".equalsIgnoreCase(tipo)) {
                    ativo = new FundoImobiliario(null, dados[2], Integer.parseInt(dados[4]), new BigDecimal(dados[5]), dados[1], dados[3]);
                }

                if (ativo != null) {
                    ativoService.salvar(ativo);
                }
            }
        }
        System.out.println(">>> [DataLoader] Carga de dados finalizada. Confirmando:");
        ativoService.listarTodos().forEach(System.out::println);
    }
}