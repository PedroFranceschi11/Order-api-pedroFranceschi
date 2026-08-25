package com.pedrofranceschi.orderapi.controller;

import com.pedrofranceschi.orderapi.dto.FornecedorResponseDTO;
import com.pedrofranceschi.orderapi.entities.Estado;
import com.pedrofranceschi.orderapi.entities.Fornecedor;
import com.pedrofranceschi.orderapi.services.FornecedorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/fornecedores")
@AllArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @GetMapping
    public ResponseEntity<List<FornecedorResponseDTO>> findAll() {
        return ResponseEntity.ok().body(fornecedorService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FornecedorResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(fornecedorService.findById(id));
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity <List<FornecedorResponseDTO>> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(fornecedorService.findByNome(nome));
    }
}
