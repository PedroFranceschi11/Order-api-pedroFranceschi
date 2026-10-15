package com.pedrofranceschi.orderapi.controller;
import com.pedrofranceschi.orderapi.dto.ClienteRequestDTO;
import com.pedrofranceschi.orderapi.dto.ClienteResponseDTO;
import com.pedrofranceschi.orderapi.dto.PedidoRequestDTO;
import com.pedrofranceschi.orderapi.dto.PedidoResponseDTO;
import com.pedrofranceschi.orderapi.entities.Pedido;
import com.pedrofranceschi.orderapi.repositories.PedidoRepository;
import com.pedrofranceschi.orderapi.services.PedidoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
@AllArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> findAll() {
        return ResponseEntity.ok().body(pedidoService.findAll());
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<PedidoResponseDTO> findByiD(@PathVariable Long id) {
        return ResponseEntity.ok().body(pedidoService.findById(id));
    }

    @GetMapping(value = "/cliente/{ClienteId}")
    public ResponseEntity<List<PedidoResponseDTO>> findByClienteId(@PathVariable Long ClienteId) {
        return ResponseEntity.ok().body(pedidoService.findByClienteId(ClienteId));
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> insert(@RequestBody @Valid PedidoRequestDTO pedido){
        PedidoResponseDTO response = pedidoService.insert(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> update(@RequestBody @Valid PedidoRequestDTO pedido, @PathVariable Long id){
        return ResponseEntity.ok().body(pedidoService.update(pedido, id));
    }



}
