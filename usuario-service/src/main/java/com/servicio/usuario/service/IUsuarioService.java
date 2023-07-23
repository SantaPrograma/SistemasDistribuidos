package com.servicio.usuario.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.usuario.dao.UsuarioDao;
import com.servicio.usuario.feignclients.EnvioFeignClient;
import com.servicio.usuario.feignclients.PedidoFeignClient;
import com.servicio.usuario.model.Envio;
import com.servicio.usuario.model.Pedido;
import com.servicio.usuario.model.Usuario;

@Service
public class IUsuarioService {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private PedidoFeignClient pedidoFeignClient;
	
	@Autowired
	private EnvioFeignClient envioFeignClient;
	
	public List<Usuario> getAll() {
		return usuarioDao.findAll();
	}
	
	public Usuario getUsuarioById(int id) {
		return usuarioDao.findById(id).orElse(null);
	}
	
	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioDao.save(usuario);
		return nuevoUsuario;
	}
	
	public Pedido savePedido(int usuarioId, Pedido pedido) {
		pedido.setUsuarioId(usuarioId);
		Pedido nuevoPedido = pedidoFeignClient.save(pedido);
		return nuevoPedido;
	}
	
	public Envio saveEnvio(int usuarioId, Envio envio) {
		envio.setUsuarioId(usuarioId);
		Envio nuevoEnvio = envioFeignClient.save(envio);
		return nuevoEnvio;
	}
	
	public Map<String, Object> getUsuarioAndEncargo(int usuarioId) {
		Map<String, Object> resultado = new HashMap<>();
		Usuario usuario = usuarioDao.findById(usuarioId).orElse(null);
		
		if(usuario==null) {
			resultado.put("Mensaje", "El usuario NO EXISTE");
			return resultado;
		}
		
		resultado.put("Usuario", usuario);
		
		List<Pedido> pedidos = pedidoFeignClient.getPedidos(usuarioId);
		if(pedidos.isEmpty()) {
			resultado.put("Pedidos", "El usuario NO TIENE PEDIDOS");
		} else {
			resultado.put("Pedidos", pedidos);
		}
		
		List<Envio> envios = envioFeignClient.getEnvios(usuarioId);
		if(envios.isEmpty()) {
			resultado.put("Envios", "El usuario NO TIENE ENVIOS");
		} else {
			resultado.put("Envios", envios);
		}
		return resultado;
	}
}
