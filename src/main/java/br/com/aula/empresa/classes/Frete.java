package br.com.aula.empresa.classes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Frete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String quemPaga;

    @NotBlank
    private String numeroConhecimento;

    @NotNull
    private Double peso;

    @NotNull
    private Double valor;

    @NotNull
    private Double icms;

    @NotNull
    private Double pedagio;

    @NotNull
    private String dataFrete;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "origem_id")
    private Cidade origem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destino_id")
    private Cidade destino;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "remetente_id")
    private Cliente remetente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destinatario_id")
    private Cliente destinatario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
}