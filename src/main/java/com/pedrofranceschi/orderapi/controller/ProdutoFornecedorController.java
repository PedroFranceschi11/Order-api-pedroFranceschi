package com.pedrofranceschi.orderapi.controller;

import com.pedrofranceschi.orderapi.dto.ProdutoFornecedorRequestDTO;
import com.pedrofranceschi.orderapi.dto.ProdutoFornecedorResponseDTO;

import com.pedrofranceschi.orderapi.services.ProdutoFornecedorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos-fornecedores")
@AllArgsConstructor
public class ProdutoFornecedorController {
    private final ProdutoFornecedorService produtoFornecedorService;

    @GetMapping(value = "/fornecedores/{fornecedorId}")
    public ResponseEntity<List<ProdutoFornecedorResponseDTO>> findByFornecedorId(@PathVariable Long fornecedorId) {
        return ResponseEntity.ok().body(produtoFornecedorService.findByFornecedorId(fornecedorId));
    }

    @GetMapping(value = "/produtos/{produtoId}")
    public ResponseEntity<List<ProdutoFornecedorResponseDTO>> findByProdutoId(@PathVariable Long produtoId) {
        return ResponseEntity.ok().body(produtoFornecedorService.findByProdutoId(produtoId));
    }

    @GetMapping(value = "/produtos/nome/{nome}")
    public ResponseEntity<List<ProdutoFornecedorResponseDTO>> findProdutoByNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(produtoFornecedorService.findByNome(nome));
    }

    @PostMapping
    public ResponseEntity<ProdutoFornecedorResponseDTO> insert(@Valid @RequestBody ProdutoFornecedorRequestDTO dto) {
        ProdutoFornecedorResponseDTO response = produtoFornecedorService.vincularEntidade(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoFornecedorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
