package com.meusgastos.meusgastos.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "centro_custo", schema = "public")
public class CentroCusto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1, schema = "public")
    @Column(name = "id_centro_custo", unique = true)
    private Long idCentroCusto;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @OneToMany(mappedBy = "centroCusto")
    @JsonBackReference
    @JsonIgnoreProperties("titulos")
    private List<Titulo> titulos;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;


}
