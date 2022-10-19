package com.example.dsevolution.teste.services;

import com.example.dsevolution.teste.entities.UsuarioSistema;
import com.example.dsevolution.teste.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioSistema criandoUser (UsuarioSistema usuario){
        return repository.save(usuario);
    }
}