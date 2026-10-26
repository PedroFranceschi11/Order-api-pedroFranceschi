package com.pedrofranceschi.orderapi.dto;


import com.pedrofranceschi.orderapi.entities.Produto;
import com.pedrofranceschi.orderapi.entities.enums.Categoria;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private MarcaResponseDTO marca;

    private Categoria categoria;

    public ProdutoResponseDTO(Produto produto) {
       this.id = produto.getId();
       this.nome = produto.getNome();
       this.descricao = produto.getDescricao();
       this.preco = produto.getPreco();
       this.marca = new MarcaResponseDTO(produto.getMarca());
       this.categoria = produto.getCategoria();
    }

}
