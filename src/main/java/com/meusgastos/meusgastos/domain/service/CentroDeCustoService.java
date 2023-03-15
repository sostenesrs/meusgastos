package com.meusgastos.meusgastos.domain.service;

import com.meusgastos.meusgastos.domain.exception.ResourceNotFoundRequestException;
import com.meusgastos.meusgastos.domain.model.CentroCusto;
import com.meusgastos.meusgastos.domain.model.Usuario;
import com.meusgastos.meusgastos.domain.repository.CentroDeCustoRepository;
import com.meusgastos.meusgastos.dto.CentroDeCustoRequestDto;
import com.meusgastos.meusgastos.dto.CentroDeCustoResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CentroDeCustoService {

    @Autowired
    private CentroDeCustoRepository centroDeCustoRepository;

    @Autowired
    private ModelMapper mapper;

    public List<CentroDeCustoResponseDto> buscarTodos(){
        List<CentroCusto> lista = centroDeCustoRepository.findAll();

        if(lista.isEmpty()){
            throw new ResourceNotFoundRequestException("Dados não encontrados");
        }
        return lista.stream()
                .map(centroCusto -> mapper.map(centroCusto, CentroDeCustoResponseDto.class)).collect(Collectors.toList());
    }

    public CentroDeCustoResponseDto buscarPorId(Long id){
        Optional<CentroCusto> optCentroDeCusto = centroDeCustoRepository.findById(id);
        if(optCentroDeCusto.isEmpty()){
            throw new ResourceNotFoundRequestException("Não foi encontrado centro de custo com o ID informado");
        }
        return mapper.map(optCentroDeCusto.get(), CentroDeCustoResponseDto.class);
    }

    public CentroDeCustoResponseDto cadastrarNovo(CentroDeCustoRequestDto dto){
        //transforma o dto em entidade
        CentroCusto centroCusto = mapper.map(dto, CentroCusto.class);

        //descobre quem é o usuario autenticado
        Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //salva usuario autenticado no centro de custo
        centroCusto.setUsuario(usuarioAutenticado);
//        centroCusto.setIdCentroCusto(null);
        centroCusto = centroDeCustoRepository.save(centroCusto);

        return mapper.map(centroCusto, CentroDeCustoResponseDto.class);
    }

    public CentroDeCustoResponseDto atualizarCentroDeCusto(Long id, CentroDeCustoRequestDto dto){
        Optional<CentroCusto> opt = centroDeCustoRepository.findById(id);
        if(opt.isEmpty()){
            throw new ResourceNotFoundRequestException("Não foi encontrado centro de custo com o ID informado");
        }
        //transforma o dto em entidade
        CentroCusto centroCusto = mapper.map(dto, CentroCusto.class);
        //descobre quem é o usuario autenticado
        Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //salva usuario autenticado no centro de custo
        centroCusto.setUsuario(usuarioAutenticado);
        //salva no banco
        centroCusto = centroDeCustoRepository.save(centroCusto);
        //transforma a entidade em dto
        return mapper.map(centroCusto, CentroDeCustoResponseDto.class);
    }

    public String excluirCentroCusto(Long id){
        buscarPorId(id);

        centroDeCustoRepository.deleteById(id);
        return "Centro de custo removido do banco de dados";
    }


}
