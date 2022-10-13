package com.example.dsevolution.teste.resources;

import com.example.dsevolution.teste.entities.DTO.PedidoDTO;
import com.example.dsevolution.teste.entities.Pedido;
import com.example.dsevolution.teste.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<Integer> savePedido(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = service.save(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido.getId());
    }
}
