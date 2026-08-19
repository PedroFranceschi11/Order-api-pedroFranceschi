package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.entities.Cliente;
import com.pedrofranceschi.orderapi.entities.Fornecedor;
import com.pedrofranceschi.orderapi.repository.ClienteRepository;
import com.pedrofranceschi.orderapi.repository.FornecedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor findById(Long Id) {
        return fornecedorRepository.findById(Id).get();
    }

    public Fornecedor findByNome(String nome) {
        return fornecedorRepository.findByNome(nome);
    }
}
