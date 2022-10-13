package com.example.dsevolution.teste.repositories;

import com.example.dsevolution.teste.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
