package com.notas.primerProyecto.entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
public class Usuario implements Serializable {

  public Usuario() {

  }

  public Usuario(String usuario, String contrasena, byte rol, boolean activo) {
    super();
    this.usuario = usuario;
    this.contrasena = contrasena;
    this.rol = rol;
    this.activo = activo;
  }

  @GeneratedValue( strategy = GenerationType.AUTO )
  @Id
  @Column( name = "ID_USUARIO" )
  private long id;

  @Column( name = "USUARIO", unique = true, nullable = false )
  private String usuario;

  @Column( name = "CONTRASENA" )
  private String contrasena;

  @Column( name = "ROL" )
  private byte rol;

  @Column( name = "ACTIVO" )
  private boolean activo;

  public long getId() {
    return id;
  }

  public String getUsuario() {
    return usuario;
  }

  public String getContrasena() {
    return contrasena;
  }

  public byte getRol() {
    return rol;
  }

  public boolean isActivo() {
    return activo;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
  }

  public void setRol(byte rol) {
    this.rol = rol;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }

  
  
}
