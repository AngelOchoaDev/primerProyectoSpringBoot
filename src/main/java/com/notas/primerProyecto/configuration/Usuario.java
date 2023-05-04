package com.notas.primerProyecto.configuration;

public class Usuario {

  private String usuario;
  
  private String contrasena;

  private byte rol;

  private boolean activo;

  public Usuario() {
  }

  public Usuario(String usuario, String contrasena, boolean activo, byte rol) {
    this.usuario = usuario;
    this.contrasena = contrasena;
    this.activo = activo;
    this.rol = rol;
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

  @Override
  public String toString() {
    return "Usuario [usuario=" + usuario + ", contrasena=" + contrasena + ", rol=" + rol + ", activo=" + activo + "]";
  }
  
}
