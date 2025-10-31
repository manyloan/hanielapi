package br.edu.infnet.hanielapi.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FundoImobiliario extends Ativo {

    @NotBlank(message = "O código do FII não pode ser vazio.")
    @Size(min = 6, max = 6, message = "O código do FII deve ter 6 caracteres.")
    private String codigo;
    
    @NotBlank(message = "O segmento do FII não pode ser vazio.")
    private String segmento;
    
    @PositiveOrZero(message = "O valor patrimonial não pode ser negativo.")
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
               Val. Patrim.: R$ %.2f
               --------------------------------
               """.formatted(this.codigo, this.segmento, this.valorPatrimonialPorCota);
    }
}