package br.edu.infnet.hanielapi.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Ativo {
    private Long id;
    private String nome;
    private int quantidade;
    private BigDecimal precoMedio;

    public abstract String getTipo();

    @Override
    public String toString() {
        return """
               --------------------------------
               ID do Ativo: %d
               Nome:        %s
               Tipo:        %s
               Quantidade:  %d
               Preço Médio: R$ %.2f
               """.formatted(
                        this.id,
                        this.nome,
                        this.getTipo(),
                        this.quantidade,
                        this.precoMedio
                );
    }
}
