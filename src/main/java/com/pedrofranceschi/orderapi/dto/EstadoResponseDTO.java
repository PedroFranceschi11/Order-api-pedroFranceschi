package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Estado;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoResponseDTO {

    private Long id;

    private String nome;

    private String uf;

    public EstadoResponseDTO(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.uf = estado.getUf();
    }

}
