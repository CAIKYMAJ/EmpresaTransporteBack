package br.com.aula.empresa.classes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class PessoaFisica extends Cliente{

    @NotBlank
    private String nome;

    @NotBlank
    @Column(nullable = false ,unique = true)
    private String cpf;
}