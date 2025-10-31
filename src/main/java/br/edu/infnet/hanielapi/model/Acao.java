package br.edu.infnet.hanielapi.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Acao extends Ativo {

    @NotBlank(message = "O ticker da ação não pode ser vazio.")
    @Size(min = 4, max = 6, message = "O ticker deve ter entre 4 e 6 caracteres.")
    private String ticker;

    @Valid
    @NotNull(message = "A empresa associada é obrigatória.")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @OneToMany(mappedBy = "acao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Provento> proventos = new ArrayList<>();

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