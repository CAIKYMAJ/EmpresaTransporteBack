package br.com.aula.empresa.classes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class PessoaJuridica extends Cliente {

    @NotBlank
    private String razaoSocial;

    @NotBlank
    private String cnpj;

    @NotBlank
    private String inscricaoEstadual;

    @OneToOne
    @JoinColumn(name = "representante_id")
    private PessoaFisica representante;
}
