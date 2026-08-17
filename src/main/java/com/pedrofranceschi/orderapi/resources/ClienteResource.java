package com.pedrofranceschi.orderapi.resources;

import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @GetMapping
    public ResponseEntity<Cliente> findAll() {
        Cidade cidade = new Cidade();
        cidade.setId(1);

        Cliente u = new Cliente(null, "João", "19882398", "99098572", cidade);
        return ResponseEntity.ok().body(u);
    }
}
