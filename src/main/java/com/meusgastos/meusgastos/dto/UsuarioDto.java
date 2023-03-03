package com.meusgastos.meusgastos.dto;

import com.meusgastos.meusgastos.domain.model.Titulo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String foto;
    private Date dataCadastro;
    private Date dataInativacao;
//    private List<Titulo> titulos = new ArrayList<>();



}
