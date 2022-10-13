package com.example.dsevolution.teste.entities.DTO;

import com.example.dsevolution.teste.entities.Cliente;
import com.example.dsevolution.teste.entities.ItemPedido;
import com.example.dsevolution.teste.entities.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

/**  {
     "cliente": 1,
     "statusPagamento": "PAGO",
     "itemPedidos":[
                 {
                 "produto": 1,
                 "quatidade": 10
                 }
            ]
     }
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Integer cliente;
    private StatusPagamento statusPagamento;
    private Set<ItemPedidoDTO> itemPedidos;
}
