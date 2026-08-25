package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class EstadoRequestDTO {

    private String nome;

    private String uf;

}
