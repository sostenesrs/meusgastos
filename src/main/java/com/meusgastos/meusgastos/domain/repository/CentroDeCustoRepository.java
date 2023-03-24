package com.meusgastos.meusgastos.domain.repository;

import com.meusgastos.meusgastos.domain.model.CentroCusto;
import com.meusgastos.meusgastos.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CentroDeCustoRepository extends JpaRepository <CentroCusto, Long> {

    List<CentroCusto> findByUsuario (Usuario usuario);
}
