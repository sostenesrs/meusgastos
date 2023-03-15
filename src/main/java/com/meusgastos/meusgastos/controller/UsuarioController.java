package com.meusgastos.meusgastos.controller;


import com.meusgastos.meusgastos.domain.service.UsuarioService;
import com.meusgastos.meusgastos.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    //conferir a ulr de autenticacao

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/buscar-usuario")
    public ResponseEntity<List<UsuarioDto>> getList(){
        return  ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @GetMapping("/buscar-usuario/{id}")
    public ResponseEntity<UsuarioDto> buscarUsuarioPorId(@PathVariable (value = "id", required = true) Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping("/cadastrar-usuario")
    public ResponseEntity<UsuarioDto> cadastrarNovo(@RequestBody UsuarioDto usuario){
        return ResponseEntity.ok(usuarioService.cadastrarNovo(usuario));
    }

    @PutMapping("/alterar-usuario")
    public ResponseEntity<UsuarioDto> atualizarUsuario(
                                                    @RequestBody UsuarioDto usuario) throws Exception {
        return ResponseEntity.ok(usuarioService.updateUsuario(usuario));
    }

    @DeleteMapping("/excluir-usuario/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable (value = "id") Long id){
        return ResponseEntity.ok(usuarioService.deleteUsuario(id));
    }

    @DeleteMapping("/inativar-usuario/{id}")
    public ResponseEntity<String> inativarUsuario(@PathVariable (value = "id") Long id){
        return ResponseEntity.ok(usuarioService.inativarUsuario(id));
    }


}


