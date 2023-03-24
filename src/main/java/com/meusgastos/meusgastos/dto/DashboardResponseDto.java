package com.meusgastos.meusgastos.dto;

import com.meusgastos.meusgastos.dto.TituloResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class DashboardResponseDto {

    private Double totalApagar;

    private Double totalReceber;

    private Double saldo;

    private List<TituloResponseDto> titulosPagar;

    private List<TituloResponseDto> titulosReceber;

}
