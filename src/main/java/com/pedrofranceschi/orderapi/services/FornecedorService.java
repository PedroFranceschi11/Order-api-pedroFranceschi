package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.CidadeResponseDTO;
import com.pedrofranceschi.orderapi.dto.FornecedorRequestDTO;
import com.pedrofranceschi.orderapi.dto.FornecedorResponseDTO;
import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Cliente;
import com.pedrofranceschi.orderapi.entities.Fornecedor;
import com.pedrofranceschi.orderapi.repository.CidadeRepository;
import com.pedrofranceschi.orderapi.repository.ClienteRepository;
import com.pedrofranceschi.orderapi.repository.FornecedorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    private final CidadeRepository cidadeRepository;

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
        return fornecedorRepository.findByNomeContainingIgnoreCase(nome).stream().map(FornecedorResponseDTO::new).toList();
    }

    @Transactional
    public FornecedorResponseDTO insert(FornecedorRequestDTO dto) {
        Cidade cidade = cidadeRepository.findById(dto.getCidadeID())
                .orElseThrow(() -> new RuntimeException("Cidade Não encontrada com o ID: " + dto.getCidadeID()));
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.getNome());
        fornecedor.setCNPJ(dto.getCNPJ());
        fornecedor.setContato(dto.getContato());
        fornecedor.setCidade(cidade);

        fornecedor = fornecedorRepository.save(fornecedor);
        return new FornecedorResponseDTO(fornecedor);
    }
}
