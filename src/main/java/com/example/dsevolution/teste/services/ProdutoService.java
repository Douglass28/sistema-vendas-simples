package com.example.dsevolution.teste.services;

import com.example.dsevolution.teste.entities.Produto;
import com.example.dsevolution.teste.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;


    public Produto cadastrarProduto(Produto produto){
        return repository.save(produto);
    }

    public List<Produto> listaDeProdutos(){
        List<Produto> list = repository.findAll();
        return list;
    }

    public Optional<Produto> buscandoPorId(Integer id){
        Optional<Produto> produto = repository.findById(id);
        return produto;
    }

    public void upDate(Integer id, Produto produto){
        Produto obj = repository.getReferenceById(id);
        upDateProduto(obj, produto);
        repository.save(obj);
    }

    private void upDateProduto(Produto obj, Produto produto) {
        obj.setNome(produto.getNome());
        obj.setDescricao(produto.getDescricao());
        obj.setPreco(produto.getPreco());
    }

    public void deletaProduto(Integer id){
         Optional<Produto> produto = repository.findById(id);
         repository.deleteById(id);
    }

}
