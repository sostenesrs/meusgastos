package com.meusgastos.meusgastos.domain.service;

import com.meusgastos.meusgastos.domain.exception.ResourceBadRequestException;
import com.meusgastos.meusgastos.domain.exception.ResourceNotFoundRequestException;
import com.meusgastos.meusgastos.domain.model.CentroCusto;
import com.meusgastos.meusgastos.domain.model.Titulo;
import com.meusgastos.meusgastos.domain.model.Usuario;
import com.meusgastos.meusgastos.domain.repository.TituloRepository;
import com.meusgastos.meusgastos.dto.CentroDeCustoRequestDto;
import com.meusgastos.meusgastos.dto.CentroDeCustoResponseDto;
import com.meusgastos.meusgastos.dto.TituloRequestDto;
import com.meusgastos.meusgastos.dto.TituloResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TituloService {

    @Autowired
    private TituloRepository tituloRepository;

    @Autowired
    private ModelMapper mapper;

    public List<TituloResponseDto> buscarTodos(){
        List<Titulo> titulos = tituloRepository.findAll();
        if(titulos.isEmpty()){
            throw new ResourceNotFoundRequestException("Dados não encontrados");
        }
        return titulos.stream()
                .map(titulo -> mapper.map(titulo, TituloResponseDto.class)).collect(Collectors.toList());
    }

    public TituloResponseDto buscarPorId(Long id){
        Optional<Titulo> optTitulo = tituloRepository.findById(id);
        if(optTitulo.isEmpty()){
            throw new ResourceNotFoundRequestException("Não foi encontrado título com o ID informado");
        }
        return mapper.map(optTitulo.get(), TituloResponseDto.class);
    }

    public TituloResponseDto cadastrarNovo(TituloRequestDto dto){
        validarTitulo(dto);
        //transforma o dto em entidade
        Titulo titulo = mapper.map(dto, Titulo.class);
        //descobre quem é o usuario autenticado
        Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //salva usuario autenticado no centro de custo
        titulo.setUsuario(usuarioAutenticado);
    //  centroCusto.setIdCentroCusto(null);
        titulo = tituloRepository.save(titulo);

        return mapper.map(titulo, TituloResponseDto.class);
    }

    public TituloResponseDto atualizarCentroDeCusto(Long id, TituloRequestDto dto){
        Optional<Titulo> opt = tituloRepository.findById(id);
        if(opt.isEmpty()){
            throw new ResourceNotFoundRequestException("Não foi encontrado título com o ID informado");
        }
        validarTitulo(dto);
        //transforma o dto em entidade
        Titulo titulo = mapper.map(dto, Titulo.class);
        //descobre quem é o usuario autenticado
        Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //salva usuario autenticado no centro de custo
        titulo.setUsuario(usuarioAutenticado);
        //salva no banco
        titulo = tituloRepository.save(titulo);
        //transforma a entidade em dto
        return mapper.map(titulo, TituloResponseDto.class);
    }

    public String excluir(Long id){
        buscarPorId(id);

        tituloRepository.deleteById(id);
        return "Título removido do banco de dados";
    }

    private void validarTitulo(TituloRequestDto dto){
        if(dto.getTipo()==null || dto.getDataVencimento()==null || dto.getValor()==null ||dto.getDescricaoTitulo()==null){
            throw new ResourceBadRequestException("Os campos Tipo, Data de Vencimento, Valor e Descrição são obrigatórios");
        }
    }



}
