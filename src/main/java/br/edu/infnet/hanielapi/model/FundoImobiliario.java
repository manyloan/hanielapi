package br.edu.infnet.hanielapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FundoImobiliario extends Ativo {
    private String codigo;
    private String segmento;
    private BigDecimal valorPatrimonialPorCota;

    public FundoImobiliario(Long id, String nome, int quantidade, BigDecimal precoMedio, String codigo, String segmento, BigDecimal valorPatrimonialPorCota) {
        super(id, nome, quantidade, precoMedio);
        this.codigo = codigo;
        this.segmento = segmento;
        this.valorPatrimonialPorCota = valorPatrimonialPorCota;
    }

    @Override
    public String getTipo() {
        return "Fundo Imobiliário";
    }

    @Override
    public String toString() {
        return super.toString() +
               """
               Código:      %s
               Segmento:    %s
               --------------------------------
               """.formatted(this.codigo, this.segmento);
    }
}