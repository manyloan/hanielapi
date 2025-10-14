package br.edu.infnet.hanielapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    private String nome;
    private String setor;
    private String cnpj;
}