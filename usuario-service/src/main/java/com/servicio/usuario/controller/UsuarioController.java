package com.servicio.usuario.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.usuario.model.Envio;
import com.servicio.usuario.model.Pedido;
import com.servicio.usuario.model.Usuario;
import com.servicio.usuario.service.IUsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService iusuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> usuarios = iusuarioService.getAll();
		if(usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id) {
		Usuario usuario = iusuarioService.getUsuarioById(id);
		if(usuario==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
		Usuario nuevoUsuario = iusuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}
	
	@PostMapping("/pedido/{usuarioId}")
	public ResponseEntity<Pedido> guardarPedido(@PathVariable("usuarioId") int usuarioId, @RequestBody Pedido pedido) {
		Pedido nuevoPedido = iusuarioService.savePedido(usuarioId, pedido);
		return ResponseEntity.ok(nuevoPedido);
	}
	
	@PostMapping("/envio/{usuarioId}")
	public ResponseEntity<Envio> guardarEnvio(@PathVariable("usuarioId") int usuarioId, @RequestBody Envio envio) {
		Envio nuevoEnvio = iusuarioService.saveEnvio(usuarioId, envio);
		return ResponseEntity.ok(nuevoEnvio);
	}
	
	@GetMapping("/encargos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> listarTodosLosEncargos(@PathVariable("usuarioId") int usuarioId) {
		Map<String, Object> resultado = iusuarioService.getUsuarioAndEncargo(usuarioId);
		return ResponseEntity.ok(resultado);
	}
}
