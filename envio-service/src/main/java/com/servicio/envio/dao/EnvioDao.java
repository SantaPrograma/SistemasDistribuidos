package com.servicio.envio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servicio.envio.model.Envio;

@Repository
public interface EnvioDao extends JpaRepository<Envio, Integer> {
	
	List<Envio> findByUsuarioId(int usuarioId);

}
