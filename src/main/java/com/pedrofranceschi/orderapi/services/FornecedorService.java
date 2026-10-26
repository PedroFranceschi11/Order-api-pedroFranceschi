package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.CidadeResponseDTO;
import com.pedrofranceschi.orderapi.dto.FornecedorRequestDTO;
import com.pedrofranceschi.orderapi.dto.FornecedorResponseDTO;
import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Fornecedor;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundException;
import com.pedrofranceschi.orderapi.repositories.CidadeRepository;
import com.pedrofranceschi.orderapi.repositories.FornecedorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final CidadeRepository cidadeRepository;
    private final CidadeService cidadeService;


    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll();

    }


    public Fornecedor findById(Long Id) {
        return fornecedorRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com o id: " + Id));
    }

    public List<Fornecedor> findByNome(String nome) {
        List<Fornecedor> fornecedores = fornecedorRepository.findByNomeContainingIgnoreCase(nome);
        if(fornecedores.isEmpty()){
            throw new ResourceNotFoundException("Fornecedor não encontrado com o termo: " + nome);
        }
        return fornecedores;

    }

    @Transactional
    public FornecedorResponseDTO vincularEntidade(FornecedorRequestDTO dto) {
       var cidade = cidadeService.findById(dto.getCidadeID());
       var fornecedor = toEntity(dto, cidade);
       fornecedor = save(fornecedor);
       return toResponseDTO(fornecedor);

    }


    private Fornecedor save(Fornecedor fornecedor) {
        try{
            return fornecedorRepository.save(fornecedor);
        }catch (DataException e){
            throw new ResourceNotFoundException("Erro ao salvar no banco");
        }
    }

    @Transactional
    public FornecedorResponseDTO update(FornecedorRequestDTO dto, Long id) {
        Fornecedor fornecedor = findById(id);
        copyDtoToEntity(dto, fornecedor);
        return toFornecedorDTO(fornecedor);
    }

    @Transactional
    public void delete(Long id) {
        Fornecedor fornecedor = findById(id);
        fornecedorRepository.delete(fornecedor);
    }

    private Fornecedor toEntity(FornecedorRequestDTO fornecedorRequestDTO, Cidade cidade) {
        return Fornecedor.builder()
                .nome(fornecedorRequestDTO.getNome())
                .contato(fornecedorRequestDTO.getContato())
                .CNPJ(fornecedorRequestDTO.getCNPJ())
                .cidade(cidade)
                .build();

    }

    private FornecedorResponseDTO toResponseDTO(Fornecedor fornecedor) {
        return FornecedorResponseDTO.builder()
                .nome(fornecedor.getNome())
                .CNPJ(fornecedor.getCNPJ())
                .contato(fornecedor.getContato())
                .cidade(new CidadeResponseDTO(fornecedor.getCidade()))
                .build();

    }

    private FornecedorResponseDTO toFornecedorDTO(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(fornecedor);
    }

    private void copyDtoToEntity(FornecedorRequestDTO fornecedorRequestDTO, Fornecedor fornecedor) {
        fornecedor.setNome(fornecedorRequestDTO.getNome());
        fornecedor.setContato(fornecedorRequestDTO.getContato());
        Cidade cidade = cidadeRepository.findById(fornecedorRequestDTO.getCidadeID())
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada com o id: " + fornecedorRequestDTO.getCidadeID()));
        fornecedor.setCidade(cidade);
    }
}
