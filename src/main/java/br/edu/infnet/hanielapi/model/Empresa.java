package br.edu.infnet.hanielapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String setor;
    private String cnpj;

    public Empresa(String nome, String setor, String cnpj) {
        this.nome = nome;
        this.setor = setor;
        this.cnpj = cnpj;
    }
}