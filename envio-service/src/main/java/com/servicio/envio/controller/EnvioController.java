package com.servicio.envio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.envio.model.Envio;
import com.servicio.envio.service.IEnvioService;

@RestController
@RequestMapping("/envio")
public class EnvioController {

	@Autowired
	private IEnvioService ienvioService;
	
	@GetMapping
	public ResponseEntity<List<Envio>> listarEnvios() {
		List<Envio> envios = ienvioService.getAll();
		if(envios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(envios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Envio> obtenerEnvio(@PathVariable("id") int id) {
		Envio envio = ienvioService.getEnvioById(id);
		if(envio==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(envio);
	}
	
	@PostMapping
	public ResponseEntity<Envio> guardarEnvio(@RequestBody Envio envio) {
		Envio nuevoEnvio = ienvioService.save(envio);
		return ResponseEntity.ok(nuevoEnvio);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity <List<Envio>> listarEnviosPorUsuarioId(@PathVariable("usuarioId") int id) {
		List<Envio> envios = ienvioService.byUsuarioId(id);
		if(envios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(envios);
	}
}
