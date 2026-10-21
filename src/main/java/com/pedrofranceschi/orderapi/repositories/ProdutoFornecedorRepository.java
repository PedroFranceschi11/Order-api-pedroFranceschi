package com.pedrofranceschi.orderapi.repositories;


import com.pedrofranceschi.orderapi.entities.Produto;
import com.pedrofranceschi.orderapi.entities.ProdutoFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoFornecedorRepository extends JpaRepository<ProdutoFornecedor, Long> {

    List<ProdutoFornecedor> findByFornecedorId(Long fornecedorId);

    List<ProdutoFornecedor> findByProdutoId(Long produtoId);

    List<ProdutoFornecedor> findByProdutoNomeContainingIgnoreCase(String nome);

}
