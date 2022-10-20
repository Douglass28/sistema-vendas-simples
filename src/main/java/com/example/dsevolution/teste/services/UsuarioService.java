package com.example.dsevolution.teste.services;

import com.example.dsevolution.teste.entities.UsuarioSistema;
import com.example.dsevolution.teste.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioSistema criandoUser (UsuarioSistema usuario){
        return repository.save(usuario);
    }

    public List<UsuarioSistema> listaUsers(){
        return repository.findAll();
    }

    public Optional<UsuarioSistema> busaPorId(Integer id){
        Optional<UsuarioSistema> usuario = repository.findById(id);
        return usuario;
    }

    public UsuarioSistema atualizaUsuario(Integer id, UsuarioSistema usuarioSistema){
        UsuarioSistema usuario = repository.getReferenceById(id);
        upDate(usuario, usuarioSistema);
        repository.save(usuario);
        return usuario;
    }

    public void upDate(UsuarioSistema usuario, UsuarioSistema usuarioSistema){
        usuario.setPassword(usuarioSistema.getPassword());
        usuario.setUsername(usuarioSistema.getUsername());
        usuario.setRoles(usuarioSistema.getRoles());
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }
}
