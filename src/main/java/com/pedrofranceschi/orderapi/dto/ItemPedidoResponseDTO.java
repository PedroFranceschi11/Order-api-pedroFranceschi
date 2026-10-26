package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.ItemPedido;
import com.pedrofranceschi.orderapi.entities.Pedido;
import com.pedrofranceschi.orderapi.entities.Produto;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class ItemPedidoResponseDTO {

    private Long produtoId;

    private String nomeProduto;

    private BigDecimal precoUnitario;

    private Integer quantidade;

    private BigDecimal subTotal;

    public ItemPedidoResponseDTO(ItemPedido itemPedido) {
        this.produtoId = itemPedido.getProduto().getId();
        this.nomeProduto = itemPedido.getProduto().getNome();
        this.precoUnitario = itemPedido.getPreco();
        this.quantidade = itemPedido.getQuantidade();
        this.subTotal = itemPedido.getSubTotal();
    }
}
