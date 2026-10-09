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

    public List<EstadoResponseDTO> findAll() {
        return estadoRepository.findAll()
                .stream()
                .map(EstadoResponseDTO::new)
                .toList();
    }

    public EstadoResponseDTO findById(Long id) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado com o id: " + id));
        return new EstadoResponseDTO(estado);
    }

    public List<EstadoResponseDTO> findByNome (String nome){
        List<Estado> estados = estadoRepository.findByNomeContainingIgnoreCase(nome);
        if(estados.isEmpty()){
            throw new ResourceNotFoundException("Estado não encontado com o termo: " + nome);
        }
        return estados.stream().map(EstadoResponseDTO::new).toList();
    }
}
