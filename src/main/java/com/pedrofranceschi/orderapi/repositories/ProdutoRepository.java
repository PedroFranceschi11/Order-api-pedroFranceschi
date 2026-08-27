package com.pedrofranceschi.orderapi.repositories;

import com.pedrofranceschi.orderapi.entities.Produto;
import com.pedrofranceschi.orderapi.entities.enums.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeContainingIgnoreCase (String nome);

     List<Produto> findByCategoria(Categoria categoria);

}

