package com.pedrofranceschi.orderapi.controller;

import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.repository.CidadeRepository;
import com.pedrofranceschi.orderapi.services.CidadeService;
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

   private final CidadeService cidadeService;

   @GetMapping
    public ResponseEntity<List<Cidade>> findAll() {
       return ResponseEntity.ok().body(cidadeService.findAll());
   }

   @GetMapping(value = "/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(cidadeService.findById(id));
    }

    @GetMapping(value = "/estado/{estadoId}")
    public ResponseEntity<List<Cidade>> findByEstado(@PathVariable Long estadoId){
       return ResponseEntity.ok().body(cidadeService.findByEstado(estadoId));
    }
}
