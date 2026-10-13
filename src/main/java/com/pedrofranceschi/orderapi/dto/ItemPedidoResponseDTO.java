package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.ItemPedido;
import com.pedrofranceschi.orderapi.entities.Pedido;
import com.pedrofranceschi.orderapi.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ItemPedidoResponseDTO {

    private Long produtoId;

    private String nomeProduto;

    private Double precoUnitario;

    private Integer quantidade;

    private Double subTotal;

    public ItemPedidoResponseDTO(ItemPedido itemPedido) {
        this.produtoId = itemPedido.getProduto().getId();
        this.nomeProduto = itemPedido.getProduto().getNome();
        this.precoUnitario = itemPedido.getPreco();
        this.quantidade = itemPedido.getQuantidade();
        this.subTotal = itemPedido.getSubTotal();
    }
}
