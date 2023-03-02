package com.meusgastos.meusgastos.domain.service;

import com.meusgastos.meusgastos.domain.model.Usuario;
import com.meusgastos.meusgastos.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos(){
        List<Usuario> user = usuarioRepository.findAll();
        if(user.isEmpty()){
            throw new RuntimeErrorException(new Error("Não encontrado"));
        }
        return user;
    }

    public Optional<Usuario> buscarPorId(Long id){
        Optional<Usuario> usuario =usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario;
        }
        return usuario;
    }
}
