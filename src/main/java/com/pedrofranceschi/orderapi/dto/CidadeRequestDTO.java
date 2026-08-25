package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Cidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeRequestDTO {
    private String nome;

    private EstadoResponseDTO estado;

}
