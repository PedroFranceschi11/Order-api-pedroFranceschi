package com.pedrofranceschi.orderapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarcaRequestDTO {

    @NotBlank(message = "O nome é obrigatorio!")
    private String nome;

}
