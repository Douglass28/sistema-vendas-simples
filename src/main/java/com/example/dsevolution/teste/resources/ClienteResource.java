package com.example.dsevolution.teste.resources;

import com.example.dsevolution.teste.entities.Cliente;
import com.example.dsevolution.teste.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> criandoCliente(@RequestBody @Valid Cliente cliente){
        service.cadastraCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscaClientes(){
       List<Cliente> list = service.buscaClientes();
       return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Cliente>> buscaId(@PathVariable Integer id){
        Optional<Cliente> cliente = service.buscaClienteId(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> atualizaCliente(@PathVariable Integer id, @RequestBody Cliente cliente){
        service.upDate(id, cliente);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaCliente(@PathVariable Integer id){
        service.deletaCliente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
