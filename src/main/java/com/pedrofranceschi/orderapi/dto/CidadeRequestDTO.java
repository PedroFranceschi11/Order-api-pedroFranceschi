package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Cidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeRequestDTO {

    @NotBlank(message = "O nome é obrigatorio!")
    @Size(max = 32, message = "O nome deve ter no maximo 32 caracteres")
    private String nome;

    @NotNull
    @Valid
    private Long estadoID;

}
