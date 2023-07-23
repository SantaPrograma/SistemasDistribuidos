package com.servicio.envio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.envio.dao.EnvioDao;
import com.servicio.envio.model.Envio;

@Service
public class IEnvioService {

	@Autowired
	private EnvioDao envioDao;
	
	public List<Envio> getAll() {
		return envioDao.findAll();
	}
	
	public Envio getEnvioById(int id) {
		return envioDao.findById(id).orElse(null);
	}
	
	public Envio save(Envio envio) {
		Envio nuevoEnvio = envioDao.save(envio);
		return nuevoEnvio;
	}
	
	public List<Envio> byUsuarioId(int usuarioId) {
		return envioDao.findByUsuarioId(usuarioId);
	}
	
}
