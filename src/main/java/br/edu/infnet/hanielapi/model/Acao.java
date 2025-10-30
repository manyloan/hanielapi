package br.edu.infnet.hanielapi.model;

import jakarta.persistence.*; 
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Acao extends Ativo {

    private String ticker;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Acao(Long id, String nome, int quantidade, BigDecimal precoMedio, String ticker, Empresa empresa) {
        super(id, nome, quantidade, precoMedio);
        this.ticker = ticker;
        this.empresa = empresa;
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
               Empresa:     %s
               Setor:       %s
               --------------------------------
               """.formatted(this.ticker, this.empresa.getNome(), this.empresa.getSetor());
    }
}