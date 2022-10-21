package com.example.dsevolution.teste.services;

import com.example.dsevolution.teste.Application;
import com.example.dsevolution.teste.entities.UsuarioSistema;
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

        return Jwts
                .builder()
                .setSubject(usuarioSistema.getUsername())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveSeguranca)
                .compact();
    }
    
    public static void main (String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        JwtService service = context.getBean(JwtService.class);
        UsuarioSistema usuarioSistema = new UsuarioSistema(null, "douglas", "senha123");
        String token = service.gerarToken(usuarioSistema);
        System.out.println(token);
    }
}
