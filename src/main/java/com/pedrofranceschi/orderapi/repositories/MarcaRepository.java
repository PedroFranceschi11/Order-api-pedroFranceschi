package com.pedrofranceschi.orderapi.repositories;

import com.pedrofranceschi.orderapi.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    List<Marca> findByNomeContainingIgnoreCase (String nome);
}

