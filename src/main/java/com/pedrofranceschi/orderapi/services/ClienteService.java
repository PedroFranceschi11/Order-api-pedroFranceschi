package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.*;
import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Cliente;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundHandler;
import com.pedrofranceschi.orderapi.repositories.CidadeRepository;
import com.pedrofranceschi.orderapi.repositories.ClienteRepository;
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
                .orElseThrow(() -> new ResourceNotFoundHandler("Cliente não encontrado com o Id: " + id));
        return new ClienteResponseDTO(cliente);
    }

            public List<ClienteResponseDTO> findAll() {
                return clienteRepository.findAll()
                        .stream()
                        .map(ClienteResponseDTO::new)
                        .toList();
    }

    public List<ClienteResponseDTO> findByNome(String nome) {
        List<Cliente> clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);
        if(clientes.isEmpty()){
            throw new ResourceNotFoundHandler("Cliente não encontrado com o termo: " + nome);
        }
        return clientes.stream().map(ClienteResponseDTO::new).toList();

    }

    @Transactional
    public ClienteResponseDTO insert(ClienteRequestDTO dto) {
        Cidade cidade = cidadeRepository.findById(dto.getCidadeID())
                .orElseThrow(() -> new ResourceNotFoundHandler("Cliente não encontrado com o ID:  " + dto.getCidadeID()));
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCidade(cidade);
        cliente.setCNPJ(dto.getCNPJ());
        cliente.setContato(dto.getContato());

        cliente = clienteRepository.save(cliente);

        return new ClienteResponseDTO(cliente);
    }
}
