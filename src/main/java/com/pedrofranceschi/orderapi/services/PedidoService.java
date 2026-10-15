package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.*;
import com.pedrofranceschi.orderapi.entities.Cliente;
import com.pedrofranceschi.orderapi.entities.ItemPedido;
import com.pedrofranceschi.orderapi.entities.Pedido;
import com.pedrofranceschi.orderapi.entities.Produto;
import com.pedrofranceschi.orderapi.entities.enums.StatusPedido;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundException;
import com.pedrofranceschi.orderapi.repositories.ClienteRepository;
import com.pedrofranceschi.orderapi.repositories.PedidoRepository;
import com.pedrofranceschi.orderapi.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    private Pedido returnId(Long id) {
        return pedidoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com o id: " + id));
    }

    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com o id: " +id));
    return new PedidoResponseDTO(pedido);
    }

    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.findAll()
                .stream()
                .map(PedidoResponseDTO::new)
                .toList();
    }

    public List<PedidoResponseDTO> findByClienteId(Long ClienteId) {
        return pedidoRepository.findByClienteId(ClienteId).
                stream().
                map(PedidoResponseDTO::new).
                toList();
    }

    @Transactional
    public PedidoResponseDTO insert (PedidoRequestDTO dto) {
        Pedido novoPedido = toEntity(dto);
        novoPedido = pedidoRepository.save(novoPedido);
        return toPedidoDTO(novoPedido);
    }

    private Pedido toEntity(PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = new Pedido();
        copyDtoToEntity(pedidoRequestDTO, pedido);
        return pedido;
    }

    private void copyDtoToEntity(PedidoRequestDTO pedidoRequestDTO, Pedido pedido) {
        pedido.setData(LocalDateTime.now());
        Cliente cliente = clienteRepository.findById(pedidoRequestDTO.getClienteId()).
                orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        pedido.setCliente(cliente);
        preencherItens(pedidoRequestDTO, pedido);
    }

    private void preencherItens(PedidoRequestDTO pedidoRequestDTO, Pedido pedido) {
        pedido.getItens().clear();
        for(ItemPedidoRequestDTO itemDto : pedidoRequestDTO.getItens()) {
            Produto produto = produtoRepository.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemDto.getQuantidade());
            item.setPreco(produto.getPreco());
            item.setPedido(pedido);
            pedido.getItens().add(item);
        }
    }

    private PedidoResponseDTO toPedidoDTO(Pedido pedido) {
        return new PedidoResponseDTO(pedido);
    }

    @Transactional
    public PedidoResponseDTO update(PedidoRequestDTO pedidoRequestDTO, Long id) {
        Pedido pedido = returnId(id);
        copyDtoToEntity(pedidoRequestDTO, pedido);
        pedido = pedidoRepository.save(pedido);

        return toPedidoDTO(pedido);
    }

    @Transactional
    public void delete(Long id) {
        Pedido pedido = returnId(id);
        pedidoRepository.delete(pedido);

    }
}
