package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.MarcaRequestDTO;
import com.pedrofranceschi.orderapi.dto.MarcaResponseDTO;
import com.pedrofranceschi.orderapi.entities.Marca;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundException;
import com.pedrofranceschi.orderapi.repositories.MarcaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca findById(Long id) {
        return marcaRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada com o id: " + id));
    }

    public List<Marca> findByNome(String nome) {
        List<Marca> marcas = marcaRepository.findByNomeContainingIgnoreCase(nome);
        if(marcas.isEmpty()){
            throw new ResourceNotFoundException("Marca não encontrada com o termo: " + nome);
        }
        return marcas;
    }

    @Transactional
    public MarcaResponseDTO insert (MarcaRequestDTO dto) {
        Marca marca = new Marca();
        marca.setNome(dto.getNome());
        marca = marcaRepository.save(marca);
        return new MarcaResponseDTO(marca);
    }

    @Transactional
    public MarcaResponseDTO update(MarcaRequestDTO marcaRequestDTO, Long id) {
        Marca marca = findById(id);
        marca.setNome(marcaRequestDTO.getNome());
        return new MarcaResponseDTO(marca);
    }

    @Transactional
    public void delete(Long id) {
        Marca marca = findById(id);
        marcaRepository.delete(marca);
    }
}
