package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class FornecedorRequestDTO {

    private String nome;

    private String CNPJ;

    private String contato;

    private CidadeResponseDTO cidade;

}
