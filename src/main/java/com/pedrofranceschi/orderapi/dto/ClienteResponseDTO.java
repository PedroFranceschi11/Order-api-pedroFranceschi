package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class ClienteResponseDTO {

    private Long id;

    private String nome;

    private String CNPJ;

    private String contato;

    private CidadeResponseDTO cidade;

    public ClienteResponseDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.CNPJ = cliente.getCNPJ();
        this.contato = cliente.getContato();
        this.cidade = new CidadeResponseDTO(cliente.getCidade());
    }
}
