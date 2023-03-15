package com.meusgastos.meusgastos.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meusgastos.meusgastos.domain.model.CentroCusto;
import com.meusgastos.meusgastos.domain.model.ETipoTitulo;
import com.meusgastos.meusgastos.domain.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TituloRequestDto {

    private Long idTitulo;
    private String descricaoTitulo;
    private Double valor;
    private Date dataCadastro;
    private Date dataVencimento;
    private Date dataPagamento;
    private String observacaoTitulo;
    private Usuario usuario;
    private List<CentroDeCustoRequestDto> centroCusto;
    private ETipoTitulo tipo;
}
