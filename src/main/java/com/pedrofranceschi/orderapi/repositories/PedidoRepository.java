package com.pedrofranceschi.orderapi.repositories;

import com.pedrofranceschi.orderapi.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoRepository extends JpaRepository<Pedido, Long>{
}
