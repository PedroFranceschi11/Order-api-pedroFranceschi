package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Pedido;
import com.pedrofranceschi.orderapi.entities.enums.StatusPedido;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class PedidoResponseDTO {

    private Long id;

    private LocalDateTime data;

    private StatusPedido status;

    private ClienteResponseDTO cliente;

    private List<ItemPedidoResponseDTO> itens;

    public PedidoResponseDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.data = pedido.getData();
        this.status = pedido.getStatus();
        this.cliente = new ClienteResponseDTO(pedido.getCliente());
        this.itens = pedido.getItens().stream()
                .map(ItemPedidoResponseDTO::new)
                .toList();
    }
}
