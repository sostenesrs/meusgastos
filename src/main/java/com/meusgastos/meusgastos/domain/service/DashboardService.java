package com.meusgastos.meusgastos.domain.service;

import com.meusgastos.meusgastos.domain.model.ETipoTitulo;
import com.meusgastos.meusgastos.dto.TituloResponseDto;
import com.meusgastos.meusgastos.dto.DashboardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private TituloService tituloService;

    public DashboardResponseDto obterFluxoCaixa(String periodoInicial, String periodoFinal){

        List<TituloResponseDto> titulos = tituloService.buscarPorVencimento(periodoInicial, periodoFinal);

        Double totalApagar = 0.00;
        Double totalReceber = 0.00;
        List<TituloResponseDto> titulosPagar = new ArrayList<>();
        List<TituloResponseDto> titulosReceber = new ArrayList<>();
        Double saldo = 0.00;

        for(TituloResponseDto titulo : titulos){

            if(titulo.getTipo() == ETipoTitulo.PAGAR){
                totalApagar += titulo.getValor();
                titulosPagar.add(titulo);
            }
            else{
                totalReceber+= titulo.getValor();
                titulosReceber.add(titulo);
            }
        }
        saldo = totalReceber - totalApagar;

        return new DashboardResponseDto(totalApagar, totalReceber, saldo, titulosPagar, titulosReceber);
    }
}
