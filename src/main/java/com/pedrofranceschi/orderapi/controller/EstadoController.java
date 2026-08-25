package com.pedrofranceschi.orderapi.controller;

import com.pedrofranceschi.orderapi.dto.EstadoResponseDTO;
import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Estado;

import com.pedrofranceschi.orderapi.services.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/estados")
@AllArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<EstadoResponseDTO>> findAll() {
        return ResponseEntity.ok().body(estadoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EstadoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(estadoService.findById(id));
    }

    @GetMapping(value = "/uf/{uf}")
    public ResponseEntity<EstadoResponseDTO> findByUf(@PathVariable String uf){
        return ResponseEntity.ok().body(estadoService.findByUf(uf));
    }

}
