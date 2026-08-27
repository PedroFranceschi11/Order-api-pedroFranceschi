package com.pedrofranceschi.orderapi.repositories;

import com.pedrofranceschi.orderapi.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

    List<Cidade> findByEstadoId(Long estadoId);
}
