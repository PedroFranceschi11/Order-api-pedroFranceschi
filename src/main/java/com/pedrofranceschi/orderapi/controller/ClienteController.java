package com.pedrofranceschi.orderapi.controller;

import com.pedrofranceschi.orderapi.dto.ClienteRequestDTO;
import com.pedrofranceschi.orderapi.dto.ClienteResponseDTO;
import com.pedrofranceschi.orderapi.repositories.ClienteRepository;
import com.pedrofranceschi.orderapi.services.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> findAll() {
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> findByiD(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.findById(id));

    }
    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity <List<ClienteResponseDTO>> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(clienteService.findByNome(nome));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> insert(@RequestBody @Valid ClienteRequestDTO cliente){
        ClienteResponseDTO response = clienteService.insert(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
