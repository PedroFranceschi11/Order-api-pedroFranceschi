package com.pedrofranceschi.orderapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProdutoFornecedorRequestDTO {

    @NotNull(message = "o produto é obrigatório")
    private Long produtoId;

    @NotNull(message = "o fornecedor é obrigatório")
    private Long fornecedorId;

    @NotNull(message = "O prazo de entrega é obrigatório")
    @Min(value = 0, message = "O prazo não pode ser negativo")
    private LocalDate prazoEntregaDias;
    //não pode ser inferior a data de agora
    //prazo de entrega de min 3 dias

}

