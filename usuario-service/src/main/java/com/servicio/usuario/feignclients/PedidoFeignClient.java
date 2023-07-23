	package com.servicio.usuario.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.servicio.usuario.model.Pedido;

@FeignClient(name = "pedido-service", url = "http://localhost:8002")
public interface PedidoFeignClient {
	
	@PostMapping("/pedido")
	public Pedido save(@RequestBody Pedido pedido);
	
	@GetMapping("/pedido/usuario/{usuarioId}")
	public List<Pedido> getPedidos(@PathVariable("usuarioId") int usuario);
}
