package br.edu.infnet.hanielapi.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Ativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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