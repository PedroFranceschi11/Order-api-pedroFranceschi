package com.pedrofranceschi.orderapi.repository;

import com.pedrofranceschi.orderapi.entities.Marca;
import com.pedrofranceschi.orderapi.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}

