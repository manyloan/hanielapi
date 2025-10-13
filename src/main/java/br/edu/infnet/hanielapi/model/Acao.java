package br.edu.infnet.hanielapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Acao extends Ativo {
    private String ticker;
    private String setor;

    public Acao(Long id, String nome, int quantidade, BigDecimal precoMedio, String ticker, String setor) {
        super(id, nome, quantidade, precoMedio);
        this.ticker = ticker;
        this.setor = setor;
    }

    @Override
    public String getTipo() {
        return "Ação";
    }

    @Override
    public String toString() {
        return super.toString() +
               """
               Ticker:      %s
               Setor:       %s
               --------------------------------
               """.formatted(this.ticker, this.setor);
    }
}