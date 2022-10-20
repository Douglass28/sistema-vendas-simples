package com.example.dsevolution.teste.resources;


import com.example.dsevolution.teste.entities.UsuarioSistema;
import com.example.dsevolution.teste.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<UsuarioSistema>> listaUsuarios(){
        List<UsuarioSistema> usuarioSistemas = service.listaUsers();
         return ResponseEntity.ok().body(usuarioSistemas);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<UsuarioSistema>> buscaPorId(@PathVariable Integer id){
         Optional<UsuarioSistema> usuarioSistema = service.busaPorId(id);
         return ResponseEntity.ok().body(usuarioSistema);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioSistema> atualizar(@PathVariable Integer id, @RequestBody UsuarioSistema usuario){
        UsuarioSistema usuarioSistema = service.atualizaUsuario(id, usuario);
        return ResponseEntity.ok().body(usuarioSistema);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id]")
    public ResponseEntity<Void> deletaUsuario(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
