package com.meusgastos.meusgastos.controller;


import com.meusgastos.meusgastos.domain.model.Usuario;
import com.meusgastos.meusgastos.domain.service.UsuarioService;
import com.meusgastos.meusgastos.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/meusgastos")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/buscar-usuario")
    public ResponseEntity<List<Usuario>> getList(){
        return  ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @GetMapping("/buscar-usuario/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable (value = "id") Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping("/cadastrar-usuario")
    public ResponseEntity<Usuario> cadastrarNovo(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.cadastrarNovo(usuario));
    }

    @PutMapping("/alterar-usuario/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable (value = "id") Long id,
                                                    @RequestBody Usuario usuario) throws Exception {
        return ResponseEntity.ok(usuarioService.updateUsuario(usuario, id));
    }

    @DeleteMapping("/excluir-usuario/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable (value = "id") Long id){
        return ResponseEntity.ok(usuarioService.deleteUsuario(id));
    }

}


