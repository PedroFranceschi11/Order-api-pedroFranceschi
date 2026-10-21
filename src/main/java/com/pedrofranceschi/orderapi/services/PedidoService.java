package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.*;
import com.pedrofranceschi.orderapi.entities.Cliente;
import com.pedrofranceschi.orderapi.entities.ItemPedido;
import com.pedrofranceschi.orderapi.entities.Pedido;
import com.pedrofranceschi.orderapi.entities.Produto;
import com.pedrofranceschi.orderapi.entities.enums.StatusPedido;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundException;
import com.pedrofranceschi.orderapi.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com o id: " +id));
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> findByClienteId(Long ClienteId) {
        return pedidoRepository.findByClienteId(ClienteId);

    }

    @Transactional
    public PedidoResponseDTO  vincularEntidade(PedidoRequestDTO requestDTO) {
        var cliente = clienteService.findById(requestDTO.getClienteId());
        Pedido pedido = toEntity(requestDTO, cliente);
        pedido = save(pedido);
        return toResponseDTO(pedido);
    }

    @Transactional
    public Pedido save(Pedido pedido){
        try{
            return pedidoRepository.save(pedido);
        }catch (DataException e) {
            throw new ResourceNotFoundException("Erro ao salvar no banco");
        }
    }

    @Transactional
    public PedidoResponseDTO update(PedidoRequestDTO pedidoRequestDTO, Long id) {
        Pedido pedido = findById(id);
        copyDtoToEntity(pedidoRequestDTO, pedido);
        pedido = save(pedido);
        return toResponseDTO(pedido);
    }

    @Transactional
    public void delete(Long id) {
        Pedido pedido = findById(id);
        pedidoRepository.delete(pedido);
    }

    private Pedido toEntity(PedidoRequestDTO pedidoRequestDTO, Cliente cliente) {
        Pedido pedido = Pedido.builder()
                .data(LocalDateTime.now())
                .status(StatusPedido.PENDENTE)
                .cliente(cliente)
                .itens(new ArrayList<>())
                .build();

        preencherItens(pedidoRequestDTO, pedido);
        return pedido;
    }

    private void copyDtoToEntity(PedidoRequestDTO pedidoRequestDTO, Pedido pedido) {
        pedido.setData(LocalDateTime.now());
        pedido.setCliente(clienteService.findById(pedidoRequestDTO.getClienteId()));
        preencherItens(pedidoRequestDTO, pedido);
    }

    private void preencherItens(PedidoRequestDTO pedidoRequestDTO, Pedido pedido) {
        pedido.getItens().clear();
        for (ItemPedidoRequestDTO itemDto : pedidoRequestDTO.getItens()) {
            Produto produto = produtoService.findById(itemDto.getProdutoId());
            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemDto.getQuantidade());
            item.setPreco(produto.getPreco());
            item.setPedido(pedido);
            pedido.getItens().add(item);
        }
    }

    private PedidoResponseDTO toResponseDTO(Pedido pedido) {
        return PedidoResponseDTO.builder()
                .data(pedido.getData())
                .status(pedido.getStatus())
                .cliente(new ClienteResponseDTO(pedido.getCliente()))
                .itens(pedido.getItens().stream()
                        .map(this::toItemResponseDTO)
                        .toList())
                .build();
    }

    private ItemPedidoResponseDTO toItemResponseDTO(ItemPedido item) {
        return ItemPedidoResponseDTO.builder()
                .produtoId(item.getProduto().getId())
                .nomeProduto(item.getProduto().getNome())
                .quantidade(item.getQuantidade())
                .precoUnitario(item.getPreco())
                .subTotal(item.getQuantidade() * item.getPreco())
                .build();
    }

}
