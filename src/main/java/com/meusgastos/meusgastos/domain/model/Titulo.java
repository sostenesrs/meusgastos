package com.meusgastos.meusgastos.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne
    @JoinColumn(name = "idUsuario")
//    @JsonManagedReference
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_centro_custo")
//    @JsonManagedReference
    private CentroCusto centroCusto;


//
//    private ETipoTitulo tipo;
//

}
