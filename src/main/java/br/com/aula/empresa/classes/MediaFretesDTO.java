package br.com.aula.empresa.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MediaFretesDTO {

    private String estado;
    private String cidade;
    private double mediaOrigem;
    private double mediaDestino;
}