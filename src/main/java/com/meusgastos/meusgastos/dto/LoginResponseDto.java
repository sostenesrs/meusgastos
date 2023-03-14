package com.meusgastos.meusgastos.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String token;

    private UsuarioDto usuario;
}
