package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.ProdutoFornecedor;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ProdutoFornecedorResponseDTO {

    private Long id;

    private Long fornecedorId;

    private Long produtoId;

    private String nomeProduto;

    private String contatoFornecedor;


    public ProdutoFornecedorResponseDTO(ProdutoFornecedor produtoFornecedor) {
        this.id = produtoFornecedor.getId();
        this.produtoId = produtoFornecedor.getProduto().getId();
        this.nomeProduto = produtoFornecedor.getProduto().getNome();
        this.contatoFornecedor = produtoFornecedor.getFornecedor().getContato();
        this.fornecedorId = produtoFornecedor.getFornecedor().getId();
    }
}
