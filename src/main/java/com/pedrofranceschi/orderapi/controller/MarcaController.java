package com.pedrofranceschi.orderapi.controller;

import com.pedrofranceschi.orderapi.dto.MarcaRequestDTO;
import com.pedrofranceschi.orderapi.dto.MarcaResponseDTO;
import com.pedrofranceschi.orderapi.entities.Marca;
import com.pedrofranceschi.orderapi.repositories.MarcaRepository;
import com.pedrofranceschi.orderapi.services.MarcaService;
import com.pedrofranceschi.orderapi.services.ProdutoService;
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
    private final ProdutoService produtoService;
    private final MarcaRepository marcaRepository;

    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> findAll() {
        return ResponseEntity.ok().body(marcaService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Marca> findById(@PathVariable Long id) {
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<MarcaResponseDTO> update(@RequestBody @Valid MarcaRequestDTO marca, @PathVariable Long id){
        return ResponseEntity.ok().body(marcaService.update(marca, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Marca> delete(@PathVariable Long id) {
        marcaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
