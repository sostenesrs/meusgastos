package com.meusgastos.meusgastos.controller;

import com.meusgastos.meusgastos.domain.repository.TituloRepository;
import com.meusgastos.meusgastos.domain.service.TituloService;
import com.meusgastos.meusgastos.dto.CentroDeCustoRequestDto;
import com.meusgastos.meusgastos.dto.CentroDeCustoResponseDto;
import com.meusgastos.meusgastos.dto.TituloRequestDto;
import com.meusgastos.meusgastos.dto.TituloResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/titulo")
public class TituloController {

    @Autowired
    private TituloService tituloService;

    @GetMapping("/buscar-todos")
    private ResponseEntity<List<TituloResponseDto>> buscarTodos(){
        return ResponseEntity.ok(tituloService.buscarTodos());
    }

    @GetMapping("/buscar-por-id/{id}")
    private ResponseEntity<TituloResponseDto> buscarPorId(@PathVariable(value = "id", required = true) Long id){
        return ResponseEntity.ok(tituloService.buscarPorId(id));
    }

    @PostMapping("/cadastrar-novo")
    private ResponseEntity<TituloResponseDto> cadastrar(@RequestBody TituloRequestDto dto){
        TituloResponseDto response =  tituloService.cadastrarNovo(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    private ResponseEntity<TituloResponseDto> atualizar(@PathVariable Long id,
                                                               @RequestBody TituloRequestDto dto){
        return ResponseEntity.ok(tituloService.atualizar(id, dto));
    }

    @DeleteMapping("/excluir/{id}")
    private String excluir(@PathVariable(value = "id", required = true) Long id){
        return tituloService.excluir(id);
    }


}
