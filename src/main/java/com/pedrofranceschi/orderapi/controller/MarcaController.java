package com.pedrofranceschi.orderapi.controller;

import com.pedrofranceschi.orderapi.dto.MarcaRequestDTO;
import com.pedrofranceschi.orderapi.dto.MarcaResponseDTO;
import com.pedrofranceschi.orderapi.services.MarcaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/marcas")
@AllArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> findAll() {
        return ResponseEntity.ok().body(marcaService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MarcaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(marcaService.findById(id));
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity <List<MarcaResponseDTO>> findByUf(@PathVariable String nome){
        return ResponseEntity.ok().body(marcaService.findByNome(nome));
    }

    @PostMapping
    public ResponseEntity<MarcaResponseDTO> insert(@RequestBody @Valid MarcaRequestDTO marca) {
        MarcaResponseDTO response = marcaService.insert(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
