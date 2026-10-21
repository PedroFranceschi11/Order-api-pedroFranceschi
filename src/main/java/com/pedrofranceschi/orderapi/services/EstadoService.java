package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.EstadoResponseDTO;
import com.pedrofranceschi.orderapi.entities.Estado;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundException;
import com.pedrofranceschi.orderapi.repositories.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public Estado findById(Long id) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado com o id: " + id));
        return estado;
    }

    public List<Estado> findByNome (String nome){
        List<Estado> estados = estadoRepository.findByNomeContainingIgnoreCase(nome);
        return estados;
    }
}
