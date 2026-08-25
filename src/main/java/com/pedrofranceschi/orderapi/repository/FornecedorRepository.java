package com.pedrofranceschi.orderapi.repository;

import com.pedrofranceschi.orderapi.entities.Cidade;
import com.pedrofranceschi.orderapi.entities.Cliente;
import com.pedrofranceschi.orderapi.entities.Estado;
import com.pedrofranceschi.orderapi.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    List<Fornecedor> findByNome(String nome);

}

