package com.example.dsevolution.teste.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "o campo nome deve ser preenchido.")
    private String nome;

    @NotNull(message = "o campo preco deve ser preenchido.")
    private Double preco;

    @NotEmpty(message = "o campo produto deve ser preenchido")
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "produto")
    private Set<ItemPedido> itemPedidos = new HashSet<>();


    public Produto(Integer id, String nome, Double preco, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }
}
