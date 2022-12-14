package com.example.dsevolution.teste.repositories;

import com.example.dsevolution.teste.entities.UsuarioSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioSistema, Integer> {

    Optional<UsuarioSistema> findByUsername (String username);
}
