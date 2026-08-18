package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.entities.Estado;
import com.pedrofranceschi.orderapi.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public Estado findById(Integer id) {
        return estadoRepository.findById(id).get();
    }

    public Estado findByUf(String uf) {
        return estadoRepository.findByUf(uf);
    }
}
