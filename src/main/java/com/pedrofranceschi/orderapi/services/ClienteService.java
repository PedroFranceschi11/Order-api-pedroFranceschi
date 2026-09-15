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
    private final CidadeService cidadeService;

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
        Cliente novoCliente = toEntity(dto);
        novoCliente = clienteRepository.save(novoCliente);
        return toClienteDTO(novoCliente);
    }

    private Cliente toEntity(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setCidade(cidadeService.findById(clienteRequestDTO.getCidadeID()));
        cliente.setCNPJ(clienteRequestDTO.getCNPJ());
        cliente.setContato(clienteRequestDTO.getContato());

        return cliente;
    }

    private ClienteResponseDTO toClienteDTO(Cliente cliente) {
        return new ClienteResponseDTO(cliente);
    }


}
