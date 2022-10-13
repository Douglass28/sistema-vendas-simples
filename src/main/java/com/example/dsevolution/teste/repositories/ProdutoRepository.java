package com.example.dsevolution.teste.repositories;

import com.example.dsevolution.teste.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
