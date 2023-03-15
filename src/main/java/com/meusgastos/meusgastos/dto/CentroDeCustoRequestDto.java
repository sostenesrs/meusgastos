package com.meusgastos.meusgastos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CentroDeCustoRequestDto {

    private Long idCentroCusto;

    private String descricao;

    private String observacao;
}
