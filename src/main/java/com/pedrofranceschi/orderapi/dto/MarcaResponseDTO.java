package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Marca;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MarcaResponseDTO {

    private Long id;

    private String nome;

    public MarcaResponseDTO(Marca marca) {
        this.id = marca.getId();
        this.nome = marca.getNome();
    }
}
