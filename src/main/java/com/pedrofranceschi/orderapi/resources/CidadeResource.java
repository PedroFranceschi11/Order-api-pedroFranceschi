package com.pedrofranceschi.orderapi.resources;

import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Estado;
import com.pedrofranceschi.orderapi.repository.CidadeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

    private CidadeRepository repository;

    public CidadeResource(CidadeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cidade>findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(repository.findById(id).get());
    }
}
