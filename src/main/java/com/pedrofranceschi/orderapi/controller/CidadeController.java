package com.pedrofranceschi.orderapi.controller;

import com.pedrofranceschi.orderapi.dto.CidadeRequestDTO;
import com.pedrofranceschi.orderapi.dto.CidadeResponseDTO;
import com.pedrofranceschi.orderapi.dto.ClienteResponseDTO;
import com.pedrofranceschi.orderapi.dto.ProdutoResponseDTO;
import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.services.CidadeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
@AllArgsConstructor
public class CidadeController {

   private final CidadeService cidadeService;

   @GetMapping
    public ResponseEntity<List<CidadeResponseDTO>> findAll() {
       return ResponseEntity.ok().body(cidadeService.findAll());
   }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CidadeResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(cidadeService.findById(id));
    }

    @GetMapping(value = "/estado/{estadoId}")
    public ResponseEntity<List<CidadeResponseDTO>> findByEstado(@PathVariable Long estadoId){
       return ResponseEntity.ok().body(cidadeService.findByEstado(estadoId));
    }

    @PostMapping
    public ResponseEntity<CidadeResponseDTO> insert(@Valid @RequestBody CidadeRequestDTO cidadeRequestDTO) {
        CidadeResponseDTO response = cidadeService.insert(cidadeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CidadeResponseDTO> update(@Valid @RequestBody CidadeRequestDTO cidadeRequestDTO, @PathVariable Long id) {
       return ResponseEntity.ok().body(cidadeService.update(cidadeRequestDTO, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cidade> delete(@PathVariable Long id) {
       cidadeService.delete(id);
       return ResponseEntity.noContent().build();
    }

}
