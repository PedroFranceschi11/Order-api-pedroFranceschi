package com.pedrofranceschi.orderapi.services;

import com.pedrofranceschi.orderapi.dto.*;
import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Cliente;
import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundException;

import com.pedrofranceschi.orderapi.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final CidadeService cidadeService;

    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o id: " + id));
    }

    public List<Cliente> findAll() {
                return clienteRepository.findAll();
    }

    public List<Cliente> findByNome(String nome) {
        List<Cliente> clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);
        if(clientes.isEmpty()){
            throw new ResourceNotFoundException("Cliente não encontrado com o termo: " + nome);
        }
        return clientes;

    }

    @Transactional
    public ClienteResponseDTO vincularEntidade(ClienteRequestDTO dto, Long id) {
        var cidade = cidadeService.findById(dto.getCidadeID());
        Cliente cliente = toEntity(dto, cidade);
        cliente = save(cliente);
        return toResponseDTO(cliente);

    }

    public Cliente save(Cliente cliente){
        try{
            return clienteRepository.save(cliente);
        }catch (DataException e) {
            throw new ResourceNotFoundException("Erro ao salvar no banco");
        }
    }

    @Transactional
    public void delete(Long id) {
        Cliente cliente = findById(id);
        clienteRepository.delete(cliente);
    }

    @Transactional
    public ClienteResponseDTO update (ClienteRequestDTO clienteRequestDTO, Long id) {
        Cliente cliente = findById(id);
        copyDtoToEntity(clienteRequestDTO, cliente);
        cliente = save(cliente);
        return toClienteDTO(cliente);

    }

    public void copyDtoToEntity(ClienteRequestDTO clienteRequestDTO, Cliente cliente ) {
        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setCNPJ(clienteRequestDTO.getCNPJ());
        cliente.setContato(clienteRequestDTO.getContato());
        Cidade cidade = cidadeService.findById(clienteRequestDTO.getCidadeID());
        cliente.setCidade(cidade);
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente){
        return ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .CNPJ(cliente.getCNPJ())
                .contato(cliente.getContato())
                .cidade(new CidadeResponseDTO(cliente.getCidade()))
                .build();
    }

    private Cliente toEntity(ClienteRequestDTO clienteRequestDTO, Cidade cidade) {
        return Cliente.builder()
                .nome(clienteRequestDTO.getNome())
                .CNPJ(clienteRequestDTO.getCNPJ())
                .contato(clienteRequestDTO.getContato())
                .cidade(cidade)
                .build();
    }

    private ClienteResponseDTO toClienteDTO(Cliente cliente) {
        return new ClienteResponseDTO(cliente);
    }

}
