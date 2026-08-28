package com.pedrofranceschi.orderapi.repositories;

import com.pedrofranceschi.orderapi.entities.Estado;
import com.pedrofranceschi.orderapi.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    List<Estado> findByNomeContainingIgnoreCase (String nome);
}

