package com.example.dsevolution.teste.services;

import com.example.dsevolution.teste.entities.Cliente;
import com.example.dsevolution.teste.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente cadastraCliente(Cliente cliente){
        return repository.save(cliente);
    }

    public List<Cliente> buscaClientes(){
       List<Cliente> list = repository.findAll();
       return list;
    }

    public Optional<Cliente> buscaClienteId(Integer id){
        Optional<Cliente> cliente = repository.findById(id);
        return cliente;
    }

    public void upDate(Integer id, Cliente cliente){
        Cliente obj = repository.getReferenceById(id);
        upDateCliente(obj, cliente);
        repository.save(obj);
    }

    private void upDateCliente(Cliente obj, Cliente cliente){
        obj.setNome(cliente.getNome());
        obj.setEmail(cliente.getEmail());
        obj.setCpf(cliente.getCpf());
    }

    public void deletaCliente(Integer id){
        Cliente obj = repository.getReferenceById(id);
        repository.delete(obj);
    }
}
