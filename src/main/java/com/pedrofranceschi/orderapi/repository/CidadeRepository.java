package com.pedrofranceschi.orderapi.repository;

import com.pedrofranceschi.orderapi.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
    
}
