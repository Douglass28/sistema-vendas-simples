package com.example.dsevolution.teste.repositories;

import com.example.dsevolution.teste.entities.UsuarioSistema;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioSistema, UUID> {
}
