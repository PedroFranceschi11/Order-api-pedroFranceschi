package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.CidadeResponseDTO;
import com.pedrofranceschi.orderapi.dto.ClienteRequestDTO;
import com.pedrofranceschi.orderapi.dto.ClienteResponseDTO;
import com.pedrofranceschi.orderapi.dto.EstadoResponseDTO;
import com.pedrofranceschi.orderapi.entities.Cliente;
import com.pedrofranceschi.orderapi.repository.CidadeRepository;
import com.pedrofranceschi.orderapi.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;


    public ClienteResponseDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCNPJ(),
                cliente.getContato(),
                new CidadeResponseDTO(cliente.getCidade())
        );
    }

        public List<ClienteResponseDTO> findAll() {
            return clienteRepository.findAll()
                    .stream()
                    .map(ClienteResponseDTO::new)
                    .toList();
    }
}
