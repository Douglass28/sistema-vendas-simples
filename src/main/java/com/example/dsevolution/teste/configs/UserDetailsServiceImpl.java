package com.example.dsevolution.teste.configs;

import com.example.dsevolution.teste.entities.UsuarioSistema;
import com.example.dsevolution.teste.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioSistema usuarioSistema = repository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("nome de usuario não exite."));
        return usuarioSistema;
    }
}
