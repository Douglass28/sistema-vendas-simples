package com.example.dsevolution.teste.resources;

import com.example.dsevolution.teste.entities.Produto;
import com.example.dsevolution.teste.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Produto> cadastraProduto(@RequestBody Produto produto){
        service.cadastrarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<Produto>> buscarTodosProdutos(){
        List<Produto> list = service.listaDeProdutos();
        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Produto>> buscarProdutoId(@PathVariable Integer id){
        Optional<Produto> produto = service.buscandoPorId(id);
        return ResponseEntity.ok().body(produto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> atualizaProduto(@PathVariable Integer id, @RequestBody Produto produto){
        service.upDate(id, produto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletaProduto(@PathVariable Integer id){
        service.deletaProduto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
