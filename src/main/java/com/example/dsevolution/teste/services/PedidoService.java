package com.example.dsevolution.teste.services;

import com.example.dsevolution.teste.entities.Cliente;
import com.example.dsevolution.teste.entities.DTO.ItemPedidoDTO;
import com.example.dsevolution.teste.entities.DTO.PedidoDTO;
import com.example.dsevolution.teste.entities.ItemPedido;
import com.example.dsevolution.teste.entities.Pedido;
import com.example.dsevolution.teste.entities.Produto;
import com.example.dsevolution.teste.repositories.ClienteRepository;
import com.example.dsevolution.teste.repositories.ItemPedidoRepository;
import com.example.dsevolution.teste.repositories.PedidoRepository;
import com.example.dsevolution.teste.repositories.ProdutoRepository;
import com.example.dsevolution.teste.services.exceptions.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRepository produtoRepository;

    public Pedido save(PedidoDTO dto){
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente).
                orElseThrow(()-> new RegraNegocioException("Cliente não existe."));

        Pedido pedido = new Pedido();
        pedido.setData(Instant.now());
        pedido.setStatusPagamento(dto.getStatusPagamento());
        pedido.setCliente(cliente);

       Set<ItemPedido> itemsPedido = converteItems(pedido, dto.getItemPedidos());
       pedidoRepository.save(pedido);
       itemPedidoRepository.saveAll(itemsPedido);
       pedido.setItemPedidos(itemsPedido);
       return pedido;

    }

    private Set<ItemPedido> converteItems(Pedido pedido,  Set<ItemPedidoDTO> items){
        if (items.isEmpty()){
            throw new RegraNegocioException("Não é possivél criar pedido sem items.");
        }

        return items.stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository.findById(idProduto).
                            orElseThrow(()-> new RegraNegocioException("Produto não existe." + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setPedido(pedido);
                    itemPedido.setQuatidade(dto.getQuantidade());
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toSet());
    }

}
