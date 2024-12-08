package br.com.aula.empresa.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArrecadacaoDTO {

    private String cidade;
    private String estado;
    private long quantidadeFretes;
    private double valorTotalArrecadado;
}