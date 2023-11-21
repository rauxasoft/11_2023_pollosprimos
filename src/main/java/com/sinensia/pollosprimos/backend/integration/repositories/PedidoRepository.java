package com.sinensia.pollosprimos.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.pollosprimos.backend.business.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
