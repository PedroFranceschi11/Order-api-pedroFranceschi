package com.pedrofranceschi.orderapi.controller;

import com.pedrofranceschi.orderapi.entities.Estado;
import com.pedrofranceschi.orderapi.repository.EstadoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/estados")

public class EstadoController {

    private EstadoRepository estadoRepository;

    public EstadoController(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @GetMapping
    public ResponseEntity <List<Estado>> findAll() {
        return ResponseEntity.ok().body(estadoRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Estado> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(estadoRepository.findById(id).get());
    }

    @GetMapping(value = "/uf/{uf}")
     public ResponseEntity<Estado> findByUf(@PathVariable String uf) {
        return ResponseEntity.ok().body(estadoRepository.findByUf(uf));
    }


}
