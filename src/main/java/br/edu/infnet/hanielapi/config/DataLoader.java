package br.edu.infnet.hanielapi.config;

import br.edu.infnet.hanielapi.model.Posicao;
import br.edu.infnet.hanielapi.service.PosicaoService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component // Um outro Bean, responsavel para que o ApplicationRunner
public class DataLoader implements ApplicationRunner {
    private final PosicaoService posicaoService;

    public DataLoader(PosicaoService posicaoService) {
        this.posicaoService = posicaoService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:posicoes.txt")))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");

                Posicao posicao = new Posicao(
                    null,
                    dados[0],
                    Integer.parseInt(dados[1]),
                    new BigDecimal(dados[2]),
                    LocalDate.parse(dados[3])
                );

                posicaoService.salvar(posicao);
            }
        }

        System.out.println("Dados carregados com sucesso!");

        posicaoService.listarTodos().forEach(System.out::println);
    }

}
