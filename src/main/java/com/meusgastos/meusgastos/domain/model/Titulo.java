package com.meusgastos.meusgastos.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "titulos", schema = "public")
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

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    @JsonIgnore(value = true)
    private Usuario usuario;
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
