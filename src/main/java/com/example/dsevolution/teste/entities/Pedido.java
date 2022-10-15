package com.example.dsevolution.teste.entities;

import com.example.dsevolution.teste.entities.enums.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Instant data;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    private StatusPagamento statusPagamento;

    @JsonIgnore
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedidos = new ArrayList<>();

    public Pedido(Integer id, Instant data, Cliente cliente, StatusPagamento statusPagamento) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.statusPagamento = statusPagamento;
    }
}
