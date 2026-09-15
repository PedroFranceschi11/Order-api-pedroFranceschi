package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.MarcaRequestDTO;
import com.pedrofranceschi.orderapi.dto.MarcaResponseDTO;
import com.pedrofranceschi.orderapi.entities.Marca;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundHandler;
import com.pedrofranceschi.orderapi.repositories.MarcaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;


    public List<MarcaResponseDTO> findAll() {
        return marcaRepository.findAll()
                .stream()
                .map(MarcaResponseDTO::new)
                .toList();
    }

    public Marca findById(Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundHandler("Marca não encontrada com o id: " + id));
    }

    public List<MarcaResponseDTO> findByNome(String nome) {
        List<Marca> marcas = marcaRepository.findByNomeContainingIgnoreCase(nome);
        if(marcas.isEmpty()){
            throw new ResourceNotFoundHandler("Marca não encontrada com o termo: " + nome);
        }
        return marcas.
                stream().
                map(MarcaResponseDTO::new).
                toList();
    }

    public MarcaResponseDTO insert (MarcaRequestDTO dto) {
        Marca marca = new Marca();
        marca.setNome(dto.getNome());

        marca = marcaRepository.save(marca);
        return new MarcaResponseDTO(marca);
    }

}
