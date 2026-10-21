package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.CidadeRequestDTO;
import com.pedrofranceschi.orderapi.dto.CidadeResponseDTO;
import com.pedrofranceschi.orderapi.dto.ClienteRequestDTO;
import com.pedrofranceschi.orderapi.dto.EstadoResponseDTO;
import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Estado;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundException;
import com.pedrofranceschi.orderapi.repositories.CidadeRepository;
import com.pedrofranceschi.orderapi.repositories.EstadoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;
    private final EstadoService estadoService;

    public List<Cidade> findAll() {
        return cidadeRepository.findAll();

    }

    public Cidade findById(Long id) {
        return cidadeRepository.findById(id).
               orElseThrow(() ->new ResourceNotFoundException("Cidade não encontrada com o id: " + id));
    }

    public List<Cidade> findByEstado(Long estadoId) {
        List<Cidade> cidades = cidadeRepository.findByEstadoId(estadoId);
        if(cidades.isEmpty()){
            throw new ResourceNotFoundException("Estado não encontrado com o Estado Id: " + estadoId);
        }
        return cidades;
    }

    @Transactional
    public CidadeResponseDTO vincularEntidade(CidadeRequestDTO dto){
        var estado = estadoService.findById(dto.getEstadoID());
        Cidade cidade = toEntity(dto, estado);
        cidade = save(cidade);
        return toResponseDTO(cidade);
    }

    @Transactional
    public Cidade save(Cidade cidade){
        try{
            return cidadeRepository.save(cidade);
        }catch (DataException e) {
            throw new ResourceNotFoundException("Erro ao salvar no banco");
        }
    }

    private void copyDtoToEntity(CidadeRequestDTO cidadeRequestDTO, Cidade cidade){
        cidade.setNome(cidadeRequestDTO.getNome());
        Estado estado = estadoRepository.findById(cidadeRequestDTO.getEstadoID())
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado com o id: " + cidadeRequestDTO.getEstadoID()));
        cidade.setEstado(estado);

    }

    private Cidade toEntity(CidadeRequestDTO cidadeRequestDTO, Estado estado){
        return Cidade.builder()
                .nome(cidadeRequestDTO.getNome())
                .estado(estado)
                .build();
    }

    private CidadeResponseDTO toResponseDTO(Cidade cidade){
        return CidadeResponseDTO.builder()
                .nome(cidade.getNome())
                .estado(new EstadoResponseDTO(cidade.getEstado()))
                .build();
    }

    private CidadeResponseDTO toCidadeDTO(Cidade cidade) {
        return new CidadeResponseDTO(cidade);
    }

    @Transactional
    public CidadeResponseDTO update(CidadeRequestDTO dto, Long id) {
        Cidade cidade = findById(id);
        copyDtoToEntity(dto, cidade);
        return toCidadeDTO(cidade);
    }

    @Transactional
    public void delete(Long id) {
        Cidade cidade = findById(id);
        cidadeRepository.deleteById(id);
    }



}
