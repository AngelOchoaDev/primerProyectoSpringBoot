package com.notas.primerProyecto.usuarios.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notas.primerProyecto.usuarios.entity.Usuario;

@Repository("usuarioRepositorio")
public interface UsuarioRepositorio extends JpaRepository<Usuario, Serializable> {

  public abstract Usuario findByUsuario(String nombreUsuario);
  
}
