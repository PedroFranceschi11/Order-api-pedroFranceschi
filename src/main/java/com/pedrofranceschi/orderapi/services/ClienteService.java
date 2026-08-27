package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.*;
import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Cliente;
import com.pedrofranceschi.orderapi.repository.CidadeRepository;
import com.pedrofranceschi.orderapi.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final CidadeRepository cidadeRepository;

    public ClienteResponseDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new ClienteResponseDTO(cliente);
    }

        public List<ClienteResponseDTO> findAll() {
            return clienteRepository.findAll()
                    .stream()
                    .map(ClienteResponseDTO::new)
                    .toList();
    }

    public List<ClienteResponseDTO> findByNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome).stream().map(ClienteResponseDTO::new).toList();
    }

    @Transactional
    public ClienteResponseDTO insert(ClienteRequestDTO dto) {
        Cidade cidade = cidadeRepository.findById(dto.getCidadeID())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada com o ID: " + dto.getCidadeID()));
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCidade(cidade);
        cliente.setCNPJ(dto.getCNPJ());
        cliente.setContato(dto.getContato());

        cliente = clienteRepository.save(cliente);

        return new ClienteResponseDTO(cliente);
    }
}
