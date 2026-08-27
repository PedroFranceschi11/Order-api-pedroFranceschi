package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.Marca;
import com.pedrofranceschi.orderapi.entities.enums.Categoria;
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
public class ProdutoRequestDTO {

    @NotBlank(message = "O nome no produto e obrigatorio")
    private String nome;

    @NotBlank(message = "A descrição do produto e obrigatoria")
    @Size(max = 100, message = "A descrição deve ter no maximo 100 caracteres")
    private String descricao;

    @NotNull(message = "O preço do produto e obrigatorio")
    private Double preco;

    @NotNull(message = "A marca é obrigatória")
    @Valid
    private Long marcaID;

    @NotNull(message = "A categoria do produto e obrigatoria")
    private Categoria categoria;
}
