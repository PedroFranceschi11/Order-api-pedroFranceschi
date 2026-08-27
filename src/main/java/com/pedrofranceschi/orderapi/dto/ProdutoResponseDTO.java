package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Marca;
import com.pedrofranceschi.orderapi.entities.Produto;
import com.pedrofranceschi.orderapi.entities.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoResponseDTO {

    private Long id;

    private String nome;

    private String descricao;

    private Double preco;

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
