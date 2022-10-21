package com.example.dsevolution.teste.services;

import com.example.dsevolution.teste.Application;
import com.example.dsevolution.teste.entities.UsuarioSistema;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${spring.jwt.expericacaoToken}")
    private String expericacaoToken;

    @Value("${spring.jwt.chaveSeguranca}")
    private String chaveSeguranca;

    public String gerarToken(UsuarioSistema usuarioSistema){
        Long expString = Long.valueOf(expericacaoToken);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        // Gerando Token
        return Jwts
                .builder()
                .setSubject(usuarioSistema.getUsername())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveSeguranca)
                .compact();
    }
    // Decoficando o token gerado atraves das credenciais do usuarioSistema
    private Claims obterClaims (String token) throws ExpiredJwtException {
        return Jwts
                .parser() //responsavel por codifica o token
                .setSigningKey(chaveSeguranca)
                .parseClaimsJws(token)
                .getBody();
    }

    // Verificando se o token ainda é valido
    public boolean tokenValido(String token){
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);

        }catch (Exception e){
            return  false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException{
        return (String) obterClaims(token).getSubject();
    }
    
    public static void main (String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        JwtService service = context.getBean(JwtService.class);
        UsuarioSistema usuarioSistema = new UsuarioSistema(null, "douglas", "senha123");
        String token = service.gerarToken(usuarioSistema);
        System.out.println(token);


        boolean isTokenValido = service.tokenValido(token);
        System.out.println("o token é valido : " + isTokenValido);

        System.out.println(service.obterLoginUsuario(token));
    }
}
