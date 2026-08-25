package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.CidadeResponseDTO;
import com.pedrofranceschi.orderapi.dto.EstadoResponseDTO;
import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public List<CidadeResponseDTO> findAll() {
        return cidadeRepository.findAll().stream().map(CidadeResponseDTO::new).toList();
    }

    public CidadeResponseDTO findById(Long Id) {
        Cidade cidade = cidadeRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        return  new CidadeResponseDTO(
                cidade.getId(),
                cidade.getNome(),
                new EstadoResponseDTO(cidade.getEstado())

        );
    }

    public List<CidadeResponseDTO> findByEstado(Long estadoId) {
        return cidadeRepository.findByEstadoId(estadoId).stream().map(CidadeResponseDTO::new).toList();
    }


}
