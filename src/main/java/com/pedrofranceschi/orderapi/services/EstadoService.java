package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.CidadeResponseDTO;
import com.pedrofranceschi.orderapi.dto.ClienteResponseDTO;
import com.pedrofranceschi.orderapi.dto.EstadoResponseDTO;
import com.pedrofranceschi.orderapi.entities.Cliente;
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

    public List<EstadoResponseDTO> findAll() {

        return estadoRepository.findAll()
                .stream()
                .map(EstadoResponseDTO::new)
                .toList();
    }

    public EstadoResponseDTO findById(Long id) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));
        return new EstadoResponseDTO(
                estado.getId(),
                estado.getNome(),
                estado.getUf()
        );
    }

    public EstadoResponseDTO findByUf(String uf) {
        Estado estado = estadoRepository.findByUf(uf);
                return new EstadoResponseDTO(estado);
    }
}
