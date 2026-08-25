package com.pedrofranceschi.orderapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteRequestDTO {

    private String nome;

    private String CNPJ;

    private String contato;

    private Integer cidadeID;

}
