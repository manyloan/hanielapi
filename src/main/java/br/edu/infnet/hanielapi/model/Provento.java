package br.edu.infnet.hanielapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Provento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O tipo do provento é obrigatório.")
    private String tipo; // Ex: "Dividendo", "JCP"

    @NotNull(message = "A data de pagamento é obrigatória.")
    @PastOrPresent(message = "A data de pagamento não pode ser no futuro.")
    private LocalDate dataPagamento;

    @NotNull
    @DecimalMin(value = "0.01", message = "O valor por ação deve ser positivo.")
    private BigDecimal valorPorAcao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acao_id")
    @JsonIgnore
    private Acao acao;
}