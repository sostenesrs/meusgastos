package com.meusgastos.meusgastos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDto {

    private Long idUsuario;
    private String nome;
    private String email;
    private String foto;
    private Date dataCadastro;
}
