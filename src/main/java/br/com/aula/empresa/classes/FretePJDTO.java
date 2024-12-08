package br.com.aula.empresa.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FretePJDTO {
    private String funcionario;
    private String empresa;
    private String representante;
    private String data;
    private double valor;
}

