//package com.meusgastos.meusgastos.domain.model;
//
//import ch.qos.logback.core.joran.spi.NoAutoStart;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//
//@Entity
//@Table(name = "centro_custo", schema = "meusgastos")
//public class CentroCusto {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @Column(name = "id_centro_custo", unique = true, nullable = false)
////    private Long idCentroCusto;
////
////    @Column(name = "descricao", nullable = false)
////    private String descricao;
////
////    @Column(name = "observacao", columnDefinition = "TEXT")
////    private String observacao;
//
////    @ManyToOne
////    @JoinColumn(name = "id_usuario")
////    private Usuario usuario;
////
////    @ManyToMany(mappedBy = "centro_custo")
////    @JsonBackReference //faz com que não gere loop nas chamadas dos objetos
////    private List<Titulo> titulos;
//
//}
