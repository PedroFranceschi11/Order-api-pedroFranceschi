package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Cliente;
import com.pedrofranceschi.orderapi.entities.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class FornecedorResponseDTO {

    private Long id;

    private String nome;

    private String CNPJ;

    private String contato;

    private CidadeResponseDTO cidade;

    public FornecedorResponseDTO(Fornecedor fornecedor) {
        this.id = fornecedor.getId();
        this.nome = fornecedor.getNome();
        this.CNPJ = fornecedor.getCNPJ();
        this.contato = fornecedor.getContato();
        this.cidade = new CidadeResponseDTO(fornecedor.getCidade());
    }
}
