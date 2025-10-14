package br.edu.infnet.hanielapi.controller.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class UpdateValorPatrimonialRequest {
    private BigDecimal novoValor;
}