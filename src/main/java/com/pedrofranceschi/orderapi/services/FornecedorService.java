package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.CidadeResponseDTO;
import com.pedrofranceschi.orderapi.dto.FornecedorRequestDTO;
import com.pedrofranceschi.orderapi.dto.FornecedorResponseDTO;
import com.pedrofranceschi.orderapi.dto.ProdutoResponseDTO;
import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Fornecedor;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundHandler;
import com.pedrofranceschi.orderapi.repositories.CidadeRepository;
import com.pedrofranceschi.orderapi.repositories.FornecedorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    private final CidadeRepository cidadeRepository;
    private final CidadeService cidadeService;

    public List<FornecedorResponseDTO> findAll() {
        return fornecedorRepository.findAll()
                .stream()
                .map(FornecedorResponseDTO::new)
                .toList();
    }


    public FornecedorResponseDTO findById(Long Id) {
        Fornecedor fornecedor = fornecedorRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundHandler("Fornecedor não encontrado com o id: " + Id));
        return new FornecedorResponseDTO(fornecedor);
    }

    public List<FornecedorResponseDTO> findByNome(String nome) {
        List<Fornecedor> fornecedores = fornecedorRepository.findByNomeContainingIgnoreCase(nome);
        if(fornecedores.isEmpty()){
            throw new ResourceNotFoundHandler("Fornecedor não encontrado com o termo: " + nome);
        }
        return fornecedores.
                stream()
                .map(FornecedorResponseDTO::new).
                toList();
    }

    @Transactional
    public FornecedorResponseDTO insert(FornecedorRequestDTO dto) {
       Fornecedor novoFornecedor = toEntity(dto);
       novoFornecedor = fornecedorRepository.save(novoFornecedor);
       return toFornecedorDTO(novoFornecedor);
    }

    private Fornecedor toEntity(FornecedorRequestDTO fornecedorRequestDTO) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorRequestDTO.getNome());
        fornecedor.setCNPJ(fornecedorRequestDTO.getCNPJ());
        fornecedor.setContato(fornecedorRequestDTO.getContato());
        fornecedor.setCidade(cidadeService.findById(fornecedorRequestDTO.getCidadeID()));
        return fornecedor;
    }

    private FornecedorResponseDTO toFornecedorDTO(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(fornecedor);
    }
}
