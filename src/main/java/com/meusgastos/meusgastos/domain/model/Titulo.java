package com.meusgastos.meusgastos.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "titulos", schema = "meusgastos")
public class Titulo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_titulo", unique = true, nullable = false)
    private Long idTitulo;

    @Column(name = "descricao")
    private String descricaoTitulo;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Column(name = "data_vencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencimento;

    @Column(name = "data_pagamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacaoTitulo;
//
//    @ManyToOne
//    @JoinColumn(name = "id_usuario")
//    private Usuario usuario;
//
//    private ETipoTitulo tipo;
//
//    @ManyToMany
//    @JoinTable(
//            name = "titulo_centrodecusto",
//            joinColumns = @JoinColumn(name = "idTitulo"),
//            inverseJoinColumns = @JoinColumn(name = "idCentroCusto")
//    )

//    private List<CentroCusto> centrosCusto;

}
