package com.example.dsevolution.teste.repositories;

import com.example.dsevolution.teste.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
