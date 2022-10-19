package com.example.dsevolution.teste.resources;


import com.example.dsevolution.teste.entities.UsuarioSistema;
import com.example.dsevolution.teste.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioResource {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioSistema> registraUser(@RequestBody @Valid UsuarioSistema usuario){
        String password = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(password);
        service.criandoUser(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping
    ResponseEntity<List<UsuarioSistema>> listaUsuarios(){
        List<UsuarioSistema> usuarioSistemas = service.listaUsers();
         return ResponseEntity.ok().body(usuarioSistemas);
    }
}
