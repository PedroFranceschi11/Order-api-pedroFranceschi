package com.pedrofranceschi.orderapi.repository;

import com.pedrofranceschi.orderapi.entities.Estado;
import com.pedrofranceschi.orderapi.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

}

