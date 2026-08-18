package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.entities.Cliente;
import com.pedrofranceschi.orderapi.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long Id) {
        return clienteRepository.findById(Id).get();
    }

    public Cliente findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }
}
