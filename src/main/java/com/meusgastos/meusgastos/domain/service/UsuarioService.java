package com.meusgastos.meusgastos.domain.service;

import com.meusgastos.meusgastos.domain.model.Usuario;
import com.meusgastos.meusgastos.domain.repository.UsuarioRepository;
import com.meusgastos.meusgastos.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Os usuarios não foram encontrados com o parâmetro informado");
        }
        return user;
    }

    public UsuarioDto buscarPorId(Long id){

        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O usuario não foi encontrado com o parâmetro informado");
        }else{
            return entityToDto(user.get());
        }
    }

    public UsuarioDto cadastrarNovo(UsuarioDto usuario){
            Usuario entity = dtoToEntity(usuario);
            if (entity.getNome().isBlank()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome é um campo obrigatório");
            }
            if(entity.getEmail().isBlank()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O e-mail é um campo obrigatório");
            }else{
                if(usuarioRepository.existsByEmail(entity.getEmail())){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O e-mail informado já está sendo utilizado");
                }
            }
            if(entity.getSenha().isBlank()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O senha é um campo obrigatório");
            }
            if (entity.getIdUsuario() == null) {
                entity.setDataCadastro(new Date());
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID informado já está em uso. Não é necessário informar o novo ID");
            }
            usuarioRepository.save(entity);
            return entityToDto(entity);
    }

    public UsuarioDto updateUsuario(UsuarioDto usuario) {
        if(usuario == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados informados inválidos");
        }
        if(usuario.getId()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID informado é inválido");
        }
        Usuario temp = usuarioRepository.findById(usuario.getId()).orElse(null);
        if(temp==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID informado não foi encontrado");
        }

            Usuario novoUsuario = new Usuario();

            novoUsuario.setIdUsuario(usuario.getId());
            novoUsuario.setNome(usuario.getNome());
            novoUsuario.setFoto(usuario.getFoto());
            novoUsuario.setEmail(usuario.getEmail());
            novoUsuario.setSenha(usuario.getSenha());
            novoUsuario.setDataCadastro(novoUsuario.getDataCadastro());
            novoUsuario.setDataInativacao(usuario.getDataInativacao());

            usuarioRepository.save(novoUsuario);
            return entityToDto(novoUsuario);

    }

    public String deleteUsuario(Long id){
        if(id==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID é um campo obrigatório");
        }
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
        entity.setSenha(dto.getSenha());
        entity.setFoto(dto.getFoto());
        entity.setDataCadastro(dto.getDataCadastro());
        entity.setDataInativacao(dto.getDataInativacao());
        entity.setTitulos((dto.getTitulos()));

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
        dto.setDataInativacao(entity.getDataInativacao());
        dto.setTitulos(entity.getTitulos());

        return dto;

    }



}
