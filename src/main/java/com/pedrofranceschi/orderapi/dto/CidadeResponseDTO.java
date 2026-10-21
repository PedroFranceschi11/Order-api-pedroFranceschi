package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Estado;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CidadeResponseDTO {

    private Long id;

    private String nome;

    private EstadoResponseDTO estado;

    public CidadeResponseDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.estado = new EstadoResponseDTO(cidade.getEstado());
    }


}
