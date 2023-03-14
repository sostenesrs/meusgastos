package com.meusgastos.meusgastos.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.meusgastos.meusgastos.common.ConversorData;
import com.meusgastos.meusgastos.domain.model.ErrorResposta;
import com.meusgastos.meusgastos.domain.model.Usuario;
import com.meusgastos.meusgastos.dto.LoginRequestDto;
import com.meusgastos.meusgastos.dto.LoginResponseDto;
import com.meusgastos.meusgastos.dto.UsuarioDto;
import jakarta.servlet.FilterChain;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

//clase faz a validação da autenticação do usuário
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        super();
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

        setFilterProcessesUrl("/api/auth");
    }
    @Override //valida se a autenticação está ok
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        try {
            //pega informações e transforma, parece o modelmapping
            LoginRequestDto login = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    login.getEmail(), login.getSenha());

            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Usuário ou senha inválido(s)");
        }catch (Exception e){
            throw new InternalAuthenticationServiceException(e.getMessage());
        }
    }

//    @Override se der ok na autenticação
    protected void successfullAuthentication(HttpServletRequest request, HttpServletResponse response,
        FilterChain chain, Authentication authResult) throws IOException {

        Usuario usuario = (Usuario) authResult.getPrincipal();
        String token = jwtUtil.gerarToken(authResult);

        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setFoto(usuario.getFoto());
        usuarioDto.setDataInativacao(usuario.getDataInativacao());
        usuarioDto.setDataCadastro(usuario.getDataCadastro());
        usuarioDto.setSenha(usuario.getSenha());
        usuarioDto.setTitulos(usuario.getTitulos());

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken("Bearer " + token);
        loginResponseDto.setUsuario(usuarioDto);

        //passar retorno no corpo
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //escreve o json a partir do loginresponseDto
        response.getWriter().write(new Gson().toJson(loginResponseDto));
    }

//    @Override se der erro
    protected void unsuccessfullAuthentication(HttpServletRequest request, HttpServletResponse response,
                                               AuthenticationException failed) throws IOException {

        String dataHora = ConversorData.convererDateParaString(new Date());
        ErrorResposta erro = new ErrorResposta(dataHora, 401, "Not Authorized", failed.getMessage());

        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(erro));

    }

}
