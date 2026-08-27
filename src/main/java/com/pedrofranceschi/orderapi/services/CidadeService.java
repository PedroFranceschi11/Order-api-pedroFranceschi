package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.CidadeResponseDTO;
import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundHandler;
import com.pedrofranceschi.orderapi.repositories.CidadeRepository;
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
                .orElseThrow(() -> new ResourceNotFoundHandler("Cidade não encontrada com o id: " + Id));
        return  new CidadeResponseDTO(cidade);
    }

    public List<CidadeResponseDTO> findByEstado(Long estadoId) {
        return cidadeRepository.findByEstadoId(estadoId).stream().map(CidadeResponseDTO::new).toList();
    }


}
