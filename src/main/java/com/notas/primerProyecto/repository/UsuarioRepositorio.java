package com.notas.primerProyecto.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notas.primerProyecto.entity.Usuario;

@Repository("usuarioRepositorio")
public interface UsuarioRepositorio extends JpaRepository<Usuario, Serializable> {

  public abstract Usuario findByUsuario(String nombreUsuario);
  
}
