package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Fornecedor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class FornecedorRequestDTO {

    @NotBlank(message = "O nome é obrigatorio!")
    @Size(max = 51, message = "O nome deve ter no maximo 50 caracteres")
    private String nome;

    @NotBlank(message = "O CNPJ é obrigatorio!")
    @Size(max = 14, message = "O cnpj deve ter no maximo 14 caracteres")
    private String CNPJ;

    @NotBlank(message = "O Contato é obrigatorio!")
    private String contato;

    @NotNull(message = "O ID da cidade é obrigatório")
    private Long cidadeID;

}
