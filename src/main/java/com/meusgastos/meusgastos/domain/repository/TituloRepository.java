package com.meusgastos.meusgastos.domain.repository;

import com.meusgastos.meusgastos.domain.model.Titulo;
import com.meusgastos.meusgastos.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM public.titulos " +
            "WHERE data_vencimento " +
            "BETWEEN TO_TIMESTAMP(:periodoInicial, 'YYYY-MM-DD hh24:MI:SS') AND " +
            "TO_TIMESTAMP(:periodoFinal, 'YYYY-MM-DD hh24:MI:SS')")

    public List<Titulo>  buscarPorDataVencimento(@Param("periodoInicial") String periodoInicial,
                                          @Param("periodoFinal") String periodoFinal);

    List<Titulo> findByUsuario (Usuario usuario);
}
