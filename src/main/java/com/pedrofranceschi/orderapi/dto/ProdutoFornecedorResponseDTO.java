package com.pedrofranceschi.orderapi.dto;

import com.pedrofranceschi.orderapi.entities.ProdutoFornecedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProdutoFornecedorResponseDTO {

    private Long id;

    private Long fornecedorId;

    private Long produtoId;

    private String nomeProduto;

    private String contato;


    public ProdutoFornecedorResponseDTO(ProdutoFornecedor produtoFornecedor) {
        this.id = produtoFornecedor.getId();
        this.produtoId = produtoFornecedor.getProduto().getId();
        this.nomeProduto = produtoFornecedor.getProduto().getNome();
        this.contato = produtoFornecedor.getFornecedor().getContato();
        this.fornecedorId = produtoFornecedor.getFornecedor().getId();
    }
}
