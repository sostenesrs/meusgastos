package com.meusgastos.meusgastos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResposta {

    private  String dataHora;

    private Integer status;

    private String titulo;

    private String mensagem;

}
