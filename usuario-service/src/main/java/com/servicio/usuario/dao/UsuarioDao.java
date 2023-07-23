package com.servicio.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servicio.usuario.model.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer>{

}
