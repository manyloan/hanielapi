package br.edu.infnet.hanielapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "O nome da empresa não pode ser vazio.")
    @Size(min = 3, max = 100, message = "O nome da empresa deve ter entre 3 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O setor da empresa não pode ser vazio.")
    private String setor;

    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}-\\d{2}$", message = "O CNPJ deve estar no formato XX.XXX.XXX/XXXX-XX")
    private String cnpj;

    public Empresa(String nome, String setor, String cnpj) {
        this.nome = nome;
        this.setor = setor;
        this.cnpj = cnpj;
    }
}