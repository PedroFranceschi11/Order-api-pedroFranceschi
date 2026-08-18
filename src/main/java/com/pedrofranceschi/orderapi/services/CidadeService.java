package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    public Cidade findById(Integer Id) {
        return cidadeRepository.findById(Id).get();
    }

    public List<Cidade> findByEstado(Integer estadoId) {
        return cidadeRepository.findByEstadoId(estadoId);
    }


}
