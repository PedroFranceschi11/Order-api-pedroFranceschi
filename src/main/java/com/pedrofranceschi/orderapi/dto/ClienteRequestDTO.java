package com.pedrofranceschi.orderapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteRequestDTO {

    @NotBlank(message = "O nome é obrigatorio!")
    @Size(max = 51, message = "O nome deve ter no maximo 50 caracteres")
    private String nome;

    @NotBlank(message = "O CNPJ é obrigatório")
    @Size(max = 14, message = "O CNPJ deve ter no máximo 14 caracteres")
    private String CNPJ;

    @NotBlank(message = "O contato é obrigatório")
    private String contato;

    @NotNull(message = "O ID da cidade é obrigatório")
    private Long cidadeID;

}
