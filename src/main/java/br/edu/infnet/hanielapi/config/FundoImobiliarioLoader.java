package br.edu.infnet.hanielapi.config;

import br.edu.infnet.hanielapi.model.FundoImobiliario;
import br.edu.infnet.hanielapi.service.FundoImobiliarioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;

@Component
@Order(2)
public class FundoImobiliarioLoader implements ApplicationRunner {

    private final FundoImobiliarioService fiiService;

    public FundoImobiliarioLoader(FundoImobiliarioService fiiService) {
        this.fiiService = fiiService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("[FundoImobiliarioLoader] -> Iniciando carga de dados de FIIs...");
        try (BufferedReader reader = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:fiis.txt")))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                FundoImobiliario fii = new FundoImobiliario(
                        null,
                        dados[1], // nome do fii
                        Integer.parseInt(dados[2]), // quantidade
                        new BigDecimal(dados[3]), // precoMedio
                        dados[0], // codigo
                        dados[4],  // segmento
                        new BigDecimal(dados[5]) // valorPatrimonialPorCota
                );
                fiiService.salvar(fii);
            }
        }
        System.out.println("[FundoImobiliarioLoader] -> Carga de FIIs finalizada.");
    }
}