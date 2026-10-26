package com.pedrofranceschi.orderapi.services;
import com.pedrofranceschi.orderapi.dto.ProdutoFornecedorRequestDTO;
import com.pedrofranceschi.orderapi.dto.ProdutoFornecedorResponseDTO;
import com.pedrofranceschi.orderapi.entities.Fornecedor;
import com.pedrofranceschi.orderapi.entities.Produto;
import com.pedrofranceschi.orderapi.entities.ProdutoFornecedor;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundException;
import com.pedrofranceschi.orderapi.repositories.ProdutoFornecedorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoFornecedorService {

    private final ProdutoFornecedorRepository produtoFornecedorRepository;
    private final FornecedorService fornecedorService;
    private final ProdutoService produtoService;

    public List<ProdutoFornecedorResponseDTO> findByFornecedorId(Long fornecedorId) {
        List<ProdutoFornecedor> produtoFornecedor = produtoFornecedorRepository.findByFornecedorId(fornecedorId);
        if(produtoFornecedor.isEmpty()) {
            throw new ResourceNotFoundException("Fornecedor não encontrado com o id: " + fornecedorId);
        }
        return produtoFornecedor.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<ProdutoFornecedorResponseDTO> findByProdutoId(Long produtoId) {
        List<ProdutoFornecedor> produtoFornecedor = produtoFornecedorRepository.findByProdutoId(produtoId);
        return produtoFornecedor.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<ProdutoFornecedorResponseDTO> findByNome(String nome) {
        List<ProdutoFornecedor> produtos = produtoFornecedorRepository.findByProdutoNomeContainingIgnoreCase(nome);
        if (produtos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum produto encontrado com o termo: " + nome);
        }
        return produtos.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ProdutoFornecedorResponseDTO vincularEntidade(ProdutoFornecedorRequestDTO requestDTO) {
        var produto = produtoService.findById(requestDTO.getProdutoId());
        var fornecedor = fornecedorService.findById(requestDTO.getFornecedorId());
        validarPrazoDeEntrega(requestDTO.getPrazoEntregaDias());
        var produtoFornecedor = toEntity(produto, fornecedor, requestDTO.getPrazoEntregaDias());
        produtoFornecedor = save(produtoFornecedor);
        return toResponseDTO(produtoFornecedor);
    }

    private ProdutoFornecedor save(ProdutoFornecedor produtoFornecedor) {
        try{
            return produtoFornecedorRepository.save(produtoFornecedor);
        }catch (DataException e) {
            throw new ResourceNotFoundException("Erro ao salvar no banco");
        }
    }

    @Transactional
    public void delete(Long id) {
        ProdutoFornecedor produtoFornecedor = produtoFornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
        produtoFornecedorRepository.delete(produtoFornecedor);
    }

    private void validarPrazoDeEntrega(LocalDate dataPrevistaEntrega) {
        LocalDate dataAtualEntrega = LocalDate.now();
        LocalDate dataMinimaEntrega = dataAtualEntrega.plusDays(3);
        if (dataPrevistaEntrega.isBefore(dataMinimaEntrega)) {
            throw new IllegalArgumentException("A data prevista de entrega deve ser de no mínimo 3 dias a partir de hoje!");
        }
    }

    private ProdutoFornecedor toEntity(Produto produto, Fornecedor fornecedor, LocalDate dataPrevistaEntrega) {
        return ProdutoFornecedor.builder()
                .produto(produto)
                .fornecedor(fornecedor)
                .dataPrevistaEntrega(dataPrevistaEntrega)
                .build();
    }

    private ProdutoFornecedorResponseDTO toResponseDTO(ProdutoFornecedor produtoFornecedor) {
        return ProdutoFornecedorResponseDTO.builder()
                .fornecedorId(produtoFornecedor.getFornecedor().getId())
                .produtoId(produtoFornecedor.getProduto().getId())
                .nomeProduto(produtoFornecedor.getProduto().getNome())
                .contatoFornecedor(produtoFornecedor.getFornecedor().getContato())
                .build();
    }



}
