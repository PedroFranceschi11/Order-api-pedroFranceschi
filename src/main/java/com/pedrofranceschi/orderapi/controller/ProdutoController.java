package com.pedrofranceschi.orderapi.controller;

import com.pedrofranceschi.orderapi.dto.ProdutoRequestDTO;
import com.pedrofranceschi.orderapi.dto.ProdutoResponseDTO;
import com.pedrofranceschi.orderapi.entities.enums.Categoria;
import com.pedrofranceschi.orderapi.services.ProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> findAll() {
        return ResponseEntity.ok().body(produtoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(produtoService.findById(id));
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<List<ProdutoResponseDTO>> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(produtoService.findByNome(nome));
    }

    @GetMapping(value = "/categoria/{categoria}")
    public ResponseEntity<List<ProdutoResponseDTO>> findByCategoria(@PathVariable String categoria) {
        Categoria categoriaEnum = Categoria.valueOf(categoria.toUpperCase());
        List<ProdutoResponseDTO> list = produtoService.findByCategoria(categoriaEnum);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> insert(@Valid @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO response = produtoService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
