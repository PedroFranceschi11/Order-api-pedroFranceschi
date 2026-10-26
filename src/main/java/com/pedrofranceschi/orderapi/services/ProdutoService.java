package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.*;
import com.pedrofranceschi.orderapi.entities.Marca;
import com.pedrofranceschi.orderapi.entities.Pedido;
import com.pedrofranceschi.orderapi.entities.Produto;
import com.pedrofranceschi.orderapi.entities.ProdutoFornecedor;
import com.pedrofranceschi.orderapi.entities.enums.Categoria;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundException;
import com.pedrofranceschi.orderapi.repositories.MarcaRepository;
import com.pedrofranceschi.orderapi.repositories.ProdutoFornecedorRepository;
import com.pedrofranceschi.orderapi.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final MarcaService marcaService;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o id: " +  id));
    }

    public List<Produto> findByNome(String nome) {
        List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);
        if (produtos.isEmpty()){
            throw new ResourceNotFoundException("Nenhum produto encontrado com o termo: " + nome);
        }
        return produtos;
    }

    public List<Produto> findByCategoria(Categoria categoria ){
        List<Produto> produtos = produtoRepository.findByCategoria(categoria);
        if(produtos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum produto encontrado com o termo: " + categoria);
        }
        return produtos;
    }

    @Transactional
    public ProdutoResponseDTO vincularEntidade(ProdutoRequestDTO dto) {
        var marca = marcaService.findById(dto.getMarcaID());
        var produto = toEntity(dto, marca);
        produto = save(produto);
        return toResponseDTO(produto);
    }


    private Produto save(Produto produto) {
        try{
            return produtoRepository.save(produto);
        }catch (DataException e) {
            throw new ResourceNotFoundException("Erro ao salvar no banco");
        }
    }

    @Transactional
    public ProdutoResponseDTO update(ProdutoRequestDTO requestDTO, Long id) {
        var produto = findById(id);
        produto = save(produto);
        return toResponseDTO(produto);
    }

    @Transactional
    public void delete(Long id) {
        Produto produto = findById(id);
        produtoRepository.delete(produto);
    }

    private Produto toEntity(ProdutoRequestDTO dto, Marca marca) {
        return Produto.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .categoria(dto.getCategoria())
                .marca(marca)
                .build();
    }

    private ProdutoResponseDTO toResponseDTO(Produto produto) {
        return ProdutoResponseDTO.builder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco()) // Atribuição direta (BigDecimal para BigDecimal)
                .marca(new MarcaResponseDTO(produto.getMarca()))
                .categoria(produto.getCategoria())
                .build();
    }
}
