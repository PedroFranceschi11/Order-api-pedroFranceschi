package com.pedrofranceschi.orderapi.controller;

import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
@AllArgsConstructor
public class CidadeController {

    private final CidadeRepository repository;

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cidade>findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(repository.findById(id).get());
    }

    @GetMapping(value = "/estado/{estadoId}")
    public ResponseEntity<List<Cidade>> findByEstado(@PathVariable Integer estadoId) {
        return ResponseEntity.ok().body(repository.findByEstadoId(estadoId));
    }
}
