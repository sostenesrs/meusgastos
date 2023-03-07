package com.meusgastos.meusgastos.domain.repository;

import com.meusgastos.meusgastos.domain.model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {


}
