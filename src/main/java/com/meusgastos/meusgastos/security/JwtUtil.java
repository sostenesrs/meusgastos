package com.meusgastos.meusgastos.security;

import com.meusgastos.meusgastos.domain.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${auth.jwt.secret}")
    private String jwtSecret;

    @Value("${auth.jwt-expiration-milliseg}")
    private Long jwtExpirationMilliseg;

    public String gerarToken(Authentication authentication){

        // Ele pega a data atual e soma mais 1 dia em milliseconds
        Date dataExpiracao = new Date(new Date().getTime() + jwtExpirationMilliseg);

        // Aqui pegamos o usuário atual da autenticação.
        Usuario usuario = (Usuario) authentication.getPrincipal();

        try {

            // Aqui gero uma chave com base na nossa secret.
            Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes("UTF-8"));

            // Aqui que a magia acontece, ele gera o token aqui.
            return Jwts.builder()
                    .setSubject(usuario.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(dataExpiracao)
                    .signWith(secretKey)
                    .compact();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
//            return ResponseEntity.badRequest().build();
        }

    }

    //método que sabe descobrir de dentro do token com base na chave privada quais as permissões do usuário.
    private Claims getClaims (String token){
        try {
            Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes("UTF-8"));
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build().parseClaimsJws(token)
                    .getBody();
            return claims;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //pega quem é o dono do token (subject)
    public String getUserName(String token){
        Claims claims = getClaims(token);

        if(claims==null){
            return null;
        }

        return claims.getSubject();
    }

    //valida se o token é valido pelo email e a data de expiração
    public boolean isValidToken(String token){
        Claims claims = getClaims(token);

        if(claims==null){
            return false;
        }
        String email = claims.getSubject();
        Date dataExpiracao = claims.getExpiration();
        Date agora = new Date(System.currentTimeMillis());

        if(email != null && agora.before(dataExpiracao)){
            return true;
        }
        return false;
    }

}
