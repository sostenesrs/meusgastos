package com.meusgastos.meusgastos.controller;

import com.meusgastos.meusgastos.domain.service.DashboardService;
import com.meusgastos.meusgastos.dto.DashboardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    private ResponseEntity<DashboardResponseDto> obterFluxoCaixa(
            @RequestParam(name = "periodoInicial") String periodoInicial,
            @RequestParam(name = "periodoFinal") String periodoFinal){

        return ResponseEntity.ok(dashboardService.obterFluxoCaixa(periodoInicial, periodoFinal));
    }
}
