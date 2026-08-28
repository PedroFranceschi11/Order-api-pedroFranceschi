package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.ProdutoRequestDTO;
import com.pedrofranceschi.orderapi.dto.ProdutoResponseDTO;
import com.pedrofranceschi.orderapi.entities.Marca;
import com.pedrofranceschi.orderapi.entities.Produto;
import com.pedrofranceschi.orderapi.entities.enums.Categoria;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundHandler;
import com.pedrofranceschi.orderapi.repositories.MarcaRepository;
import com.pedrofranceschi.orderapi.repositories.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final MarcaRepository marcaRepository;

    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    public ProdutoResponseDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundHandler("Produto não encontrado com o id: " +  id));
        return new ProdutoResponseDTO(produto);
    }

    public List<ProdutoResponseDTO> findByNome(String nome) {
        List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);
        if (produtos.isEmpty()){
            throw new ResourceNotFoundHandler("Nenhum produto encontrado com o termo: " + nome);
        }
        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    public List<ProdutoResponseDTO> findByCategoria(Categoria categoria ){
        List<Produto> produtos = produtoRepository.findByCategoria(categoria);
        if(produtos.isEmpty()) {
            throw new ResourceNotFoundHandler("Nenhum produto encontrado com o termo: " + categoria);
        }
        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    public ProdutoResponseDTO insert (ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());

        Marca marca = marcaRepository.findById(dto.getMarcaID())
                .orElseThrow(() -> new ResourceNotFoundHandler("Marca não encontrada com ID: " + dto.getMarcaID()));

        produto.setMarca(marca);

        produto = produtoRepository.save(produto);
        return new ProdutoResponseDTO(produto);
    }
}
