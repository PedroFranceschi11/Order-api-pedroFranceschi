package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.ProdutoRequestDTO;
import com.pedrofranceschi.orderapi.dto.ProdutoResponseDTO;
import com.pedrofranceschi.orderapi.entities.Produto;
import com.pedrofranceschi.orderapi.entities.enums.Categoria;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundException;
import com.pedrofranceschi.orderapi.repositories.MarcaRepository;
import com.pedrofranceschi.orderapi.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final MarcaRepository marcaRepository;
    private final MarcaService marcaService;

    private Produto returnId(Long id) {
        return produtoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o id: " + id));
    }

    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    public ProdutoResponseDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o id: " +  id));
        return new ProdutoResponseDTO(produto);
    }

    public List<ProdutoResponseDTO> findByNome(String nome) {
        List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);
        if (produtos.isEmpty()){
            throw new ResourceNotFoundException("Nenhum produto encontrado com o termo: " + nome);
        }
        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    public List<ProdutoResponseDTO> findByCategoria(Categoria categoria ){
        List<Produto> produtos = produtoRepository.findByCategoria(categoria);
        if(produtos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum produto encontrado com o termo: " + categoria);
        }
        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    @Transactional
    public ProdutoResponseDTO insert (ProdutoRequestDTO dto) {
        Produto novoProduto = toEntity(dto);
        novoProduto = produtoRepository.save(novoProduto);
        return toProdutoDTO(novoProduto);
    }

    private Produto toEntity(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = new Produto();
        copyDtoToEntity(produtoRequestDTO, produto);
        return produto;
    }

    private ProdutoResponseDTO toProdutoDTO(Produto produto) {
        return new ProdutoResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO update(ProdutoRequestDTO dto, Long id) {
        Produto produto = returnId(id);
        copyDtoToEntity(dto, produto);
        return toProdutoDTO(produto);
    }

    private void copyDtoToEntity(ProdutoRequestDTO produtoRequestDTO, Produto produto) {
        produto.setNome(produtoRequestDTO.getNome());
        produto.setCategoria(produtoRequestDTO.getCategoria());
        produto.setMarca(marcaService.findById(produtoRequestDTO.getMarcaID()));
        produto.setPreco(produtoRequestDTO.getPreco());
        produto.setDescricao(produtoRequestDTO.getDescricao());
    }
    @Transactional
    public void delete(Long id) {
        Produto produto = returnId(id);
        produtoRepository.delete(produto);
    }
}
