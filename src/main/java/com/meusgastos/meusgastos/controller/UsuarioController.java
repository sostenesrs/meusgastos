package com.meusgastos.meusgastos.controller;


import com.meusgastos.meusgastos.domain.model.Usuario;
import com.meusgastos.meusgastos.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/meusgastos")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/buscar-usuarios")
    public ResponseEntity<List<Usuario>> getList(){
        return  ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @GetMapping(value = "/buscar-usuarios/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable (value = "id") Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id).get());
    }


    }


