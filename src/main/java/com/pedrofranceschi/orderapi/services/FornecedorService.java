package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.CidadeResponseDTO;
import com.pedrofranceschi.orderapi.dto.FornecedorResponseDTO;
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

    public List<FornecedorResponseDTO> findAll() {
                return fornecedorRepository.findAll().stream().map(FornecedorResponseDTO::new).toList();

    }

    public FornecedorResponseDTO findById(Long Id) {
        Fornecedor fornecedor = fornecedorRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        return new FornecedorResponseDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getCNPJ(),
                fornecedor.getContato(),
                new CidadeResponseDTO(fornecedor.getCidade())
        );

    }

    public List<FornecedorResponseDTO> findByNome(String nome) {
        return fornecedorRepository.findByNome(nome).stream().map(FornecedorResponseDTO::new).toList();
    }
}
