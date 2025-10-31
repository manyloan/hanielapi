package br.edu.infnet.hanielapi.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "O nome do ativo não pode ser vazio.")
    @Size(min = 2, max = 100, message = "O nome do ativo deve ter entre 2 e 100 caracteres.")
    private String nome;

    @Min(value = 1, message = "A quantidade deve ser de no mínimo 1.")
    private int quantidade;

    @DecimalMin(value = "0.01", message = "O preço médio deve ser positivo.")
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