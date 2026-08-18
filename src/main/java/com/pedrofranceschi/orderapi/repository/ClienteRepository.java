package com.pedrofranceschi.orderapi.repository;

import com.pedrofranceschi.orderapi.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Cliente findByNome(String nome);
}
