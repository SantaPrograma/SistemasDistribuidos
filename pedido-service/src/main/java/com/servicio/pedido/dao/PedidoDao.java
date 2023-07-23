package com.servicio.pedido.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servicio.pedido.model.Pedido;

@Repository
public interface PedidoDao extends JpaRepository<Pedido, Integer> {
	List<Pedido> findByUsuarioId(int usuarioId);
}
