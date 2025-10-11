package br.edu.infnet.hanielapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posicao {

    private Long id;
    private String ticker;
    private int quantity;
    private BigDecimal averagePrice;
    private LocalDate purchaseDate;

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return """
                ID: %d
                Ticker: %s
                Quantity: %d
                Avarage Price: %.2f
                Purchase Date: %s
                """.formatted(
                    this.id,
                    this.ticker,
                    this.quantity,
                    this.averagePrice,
                    this.purchaseDate.format(dateFormatter)
                );
    }
}
