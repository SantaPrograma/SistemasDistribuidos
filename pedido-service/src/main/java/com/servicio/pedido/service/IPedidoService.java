package com.servicio.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.pedido.dao.PedidoDao;
import com.servicio.pedido.model.Pedido;

@Service
public class IPedidoService {
	
	@Autowired
	private PedidoDao pedidoDao;
	
	public List<Pedido> getAll() {
		return pedidoDao.findAll();
	}
	
	public Pedido getPedidoById(int id) {
		return pedidoDao.findById(id).orElse(null);
	}
	
	public Pedido save(Pedido pedido) {
		Pedido nuevoPedido = pedidoDao.save(pedido);
		return nuevoPedido;
	}
	
	public List<Pedido> byUsuarioId(int usuarioId) {
		return pedidoDao.findByUsuarioId(usuarioId);
	}

}
