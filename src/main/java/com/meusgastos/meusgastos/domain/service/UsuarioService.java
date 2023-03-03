package com.meusgastos.meusgastos.domain.service;

import com.meusgastos.meusgastos.domain.model.Usuario;
import com.meusgastos.meusgastos.domain.repository.UsuarioRepository;
import com.meusgastos.meusgastos.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.management.RuntimeErrorException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos(){
        List<Usuario> user = usuarioRepository.findAll();
        if(user.isEmpty()){
            throw new RuntimeErrorException(new Error("O usuario não foi encontrado com o parâmetro informado"));
        }
        return user;
    }

    public Usuario buscarPorId(Long id){
        Optional<Usuario> user =usuarioRepository.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeErrorException(new Error("O usuario não foi encontrado com o parâmetro informado"));
        }else{
            return user.get();
        }

    }

    public Usuario cadastrarNovo(Usuario usuario){
            usuarioRepository.save(usuario);
            return usuario;
    }

    public Usuario updateUsuario(Usuario usuario, Long id) throws Exception {
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O usuario não foi encontrado com o parâmetro informado");
        }else {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setIdUsuario(id);
            novoUsuario.setNome(usuario.getNome());
            novoUsuario.setFoto(usuario.getFoto());
            novoUsuario.setEmail(usuario.getEmail());
            novoUsuario.setSenha(usuario.getSenha());
            novoUsuario.setDataCadastro(usuario.getDataCadastro());
            novoUsuario.setDataInativacao(usuario.getDataInativacao());

            usuarioRepository.save(novoUsuario);
            return novoUsuario;
        }
    }

    public String deleteUsuario(Long id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O usuario não foi encontrado com o parâmetro informado");
        }else{
            usuarioRepository.deleteById(id);
            return "Usuario "+id+" removido do banco de dados com sucesso";
        }
    }


    public Usuario dtoToEntity(UsuarioDto dto){
        Usuario entity = new Usuario();

        entity.setIdUsuario(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(entity.getSenha());
        entity.setFoto(dto.getFoto());
        entity.setDataCadastro(dto.getDataCadastro());
        entity.setDataInativacao(dto.getDataInativacao());
        return entity;
    }

    public UsuarioDto entityToDto(Usuario entity){
        UsuarioDto dto = new UsuarioDto();

        dto.setId(entity.getIdUsuario());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setSenha(entity.getSenha());
        dto.setFoto(entity.getFoto());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setDataInativacao(dto.getDataInativacao());

        return dto;

    }


}
