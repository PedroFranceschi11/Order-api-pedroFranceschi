package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.FornecedorRequestDTO;
import com.pedrofranceschi.orderapi.dto.FornecedorResponseDTO;
import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Fornecedor;
import com.pedrofranceschi.orderapi.entities.Produto;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundException;
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

    private Fornecedor returnId(Long id) {
        return fornecedorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com o id: " + id));
    }

    public List<FornecedorResponseDTO> findAll() {
        return fornecedorRepository.findAll()
                .stream()
                .map(FornecedorResponseDTO::new)
                .toList();
    }


    public FornecedorResponseDTO findById(Long Id) {
        Fornecedor fornecedor = fornecedorRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com o id: " + Id));
        return new FornecedorResponseDTO(fornecedor);
    }

    public List<FornecedorResponseDTO> findByNome(String nome) {
        List<Fornecedor> fornecedores = fornecedorRepository.findByNomeContainingIgnoreCase(nome);
        if(fornecedores.isEmpty()){
            throw new ResourceNotFoundException("Fornecedor não encontrado com o termo: " + nome);
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
        fornecedor.setCNPJ(fornecedorRequestDTO.getCNPJ());
        copyDtoToEntity(fornecedorRequestDTO, fornecedor);
        return fornecedor;
    }

    private FornecedorResponseDTO toFornecedorDTO(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(fornecedor);
    }

    @Transactional
    public FornecedorResponseDTO update(FornecedorRequestDTO dto, Long id) {
        Fornecedor fornecedor = returnId(id);
        copyDtoToEntity(dto, fornecedor);
        return toFornecedorDTO(fornecedor);
    }

    private void copyDtoToEntity(FornecedorRequestDTO fornecedorRequestDTO, Fornecedor fornecedor) {
        fornecedor.setNome(fornecedorRequestDTO.getNome());
        fornecedor.setContato(fornecedorRequestDTO.getContato());
        Cidade cidade = cidadeRepository.findById(fornecedorRequestDTO.getCidadeID())
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada com o id: " + fornecedorRequestDTO.getCidadeID()));
        fornecedor.setCidade(cidade);
    }

    @Transactional
    public void delete(Long id) {
        Fornecedor fornecedor = returnId(id);
        fornecedorRepository.delete(fornecedor);
    }
}
