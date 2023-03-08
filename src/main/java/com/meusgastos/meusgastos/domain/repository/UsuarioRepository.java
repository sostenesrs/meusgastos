package com.meusgastos.meusgastos.domain.repository;

import com.meusgastos.meusgastos.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public boolean existsByEmail(String email);

}
