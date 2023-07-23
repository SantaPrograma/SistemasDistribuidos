package com.servicio.pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.pedido.model.Pedido;
import com.servicio.pedido.service.IPedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private IPedidoService ipedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> listarPedidos() {
		List<Pedido> pedidos = ipedidoService.getAll();
		if(pedidos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(pedidos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> obtenerPedido(@PathVariable("id") int id) {
		Pedido pedido = ipedidoService.getPedidoById(id);
		if(pedido==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pedido);
	}
	
	@PostMapping
	public ResponseEntity<Pedido> guardarPedido(@RequestBody Pedido pedido) {
		Pedido nuevoPedido = ipedidoService.save(pedido);
		return ResponseEntity.ok(nuevoPedido);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity <List<Pedido>> listarPedidosPorUsuarioId(@PathVariable("usuarioId") int id) {
		List<Pedido> pedidos = ipedidoService.byUsuarioId(id);
		if(pedidos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(pedidos);
	}
}
