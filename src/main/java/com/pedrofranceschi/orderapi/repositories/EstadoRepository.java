package com.pedrofranceschi.orderapi.repositories;

import com.pedrofranceschi.orderapi.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Estado findByUf(String uf);
}

