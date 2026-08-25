package com.pedrofranceschi.orderapi.repository;

import com.pedrofranceschi.orderapi.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
