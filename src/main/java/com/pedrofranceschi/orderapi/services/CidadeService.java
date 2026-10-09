package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.CidadeRequestDTO;
import com.pedrofranceschi.orderapi.dto.CidadeResponseDTO;
import com.pedrofranceschi.orderapi.dto.ClienteRequestDTO;
import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Estado;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundException;
import com.pedrofranceschi.orderapi.repositories.CidadeRepository;
import com.pedrofranceschi.orderapi.repositories.EstadoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;
    private final EstadoService estadoService;

    private Cidade returnId(Long id) {
        return cidadeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrado com o id: " + id));
    }

    public List<CidadeResponseDTO> findAll() {
        return cidadeRepository.findAll()
                .stream()
                .map(CidadeResponseDTO::new)
                .toList();
    }

    public CidadeResponseDTO findById(Long id) {
        return cidadeRepository.findById(id)
                .map(CidadeResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada com o id: " + id));
    }

    public List<CidadeResponseDTO> findByEstado(Long estadoId) {
        List<Cidade> cidades = cidadeRepository.findByEstadoId(estadoId);
        if(cidades.isEmpty()){
            throw new ResourceNotFoundException("Estado não encontrado com o Estado Id: " + estadoId);
        }
        return cidades.stream().map(CidadeResponseDTO::new).toList();
    }

    @Transactional
    public CidadeResponseDTO insert(CidadeRequestDTO dto){
        Cidade novaCidade = toEntity(dto);
        novaCidade = cidadeRepository.save(novaCidade);
        return toCidadeDTO(novaCidade);
    }

    private void copyDtoToEntity(CidadeRequestDTO cidadeRequestDTO, Cidade cidade){
        cidade.setNome(cidadeRequestDTO.getNome());
        Estado estado = estadoRepository.findById(cidadeRequestDTO.getEstado().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado com o id: " + cidadeRequestDTO.getEstado().getId()));
        cidade.setEstado(estado);

    }

    private Cidade toEntity(CidadeRequestDTO cidadeRequestDTO){
        Cidade cidade = new Cidade();
        copyDtoToEntity(cidadeRequestDTO, cidade);
        return cidade;
    }

    private CidadeResponseDTO toCidadeDTO(Cidade cidade) {
        return new CidadeResponseDTO(cidade);
    }

    public CidadeResponseDTO update(CidadeRequestDTO dto, Long id) {
        Cidade cidade = returnId(id);
        copyDtoToEntity(dto, cidade);
        return toCidadeDTO(cidade);
    }

    public void delete(Long id) {
        Cidade cidade = returnId(id);
        cidadeRepository.deleteById(id);
    }

}
