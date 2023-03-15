package com.meusgastos.meusgastos.controller;

import com.meusgastos.meusgastos.domain.service.CentroDeCustoService;
import com.meusgastos.meusgastos.dto.CentroDeCustoRequestDto;
import com.meusgastos.meusgastos.dto.CentroDeCustoResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/centrodecusto")
public class CentroDeCustoController {

    @Autowired
    private CentroDeCustoService centroDeCustoService;

    @GetMapping("/buscar-todos")
    private ResponseEntity<List<CentroDeCustoResponseDto>> buscarTodos(){
        return ResponseEntity.ok(centroDeCustoService.buscarTodos());
    }

    @GetMapping("/buscar-por-id/{id}")
    private ResponseEntity<CentroDeCustoResponseDto> buscarPorId(@PathVariable (value = "id", required = true) Long id){
        return ResponseEntity.ok(centroDeCustoService.buscarPorId(id));
    }

    @PostMapping("/cadastrar-novo")
    private ResponseEntity<CentroDeCustoResponseDto> cadastrar(@RequestBody CentroDeCustoRequestDto dto){
        CentroDeCustoResponseDto response =  centroDeCustoService.cadastrarNovo(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    private ResponseEntity<CentroDeCustoResponseDto> atualizar(@PathVariable Long id,
                                                               @RequestBody CentroDeCustoRequestDto dto){
        return ResponseEntity.ok(centroDeCustoService.atualizarCentroDeCusto(id, dto));
    }

    @DeleteMapping("/excluir/{id}")
    private String excluir(@PathVariable(value = "id", required = true) Long id){
        return centroDeCustoService.excluirCentroCusto(id);
    }



}
