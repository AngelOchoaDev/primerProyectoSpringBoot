package com.notas.primerProyecto.notas.entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity(name = "nota")
public class Nota implements Serializable {

  //Columnas de la tabla
  @GeneratedValue( strategy = GenerationType.AUTO )
  @Id
  @Column( name = "ID_NOTA" )
  private int idNota;
  
  @Column( name = "NOMBRE" )
  private String nombre;

  @Column( name = "TITULO" )
  private String titulo;

  @Column( name = "CONTENIDO" )
  private String contenido;

  @Column( name = "ID_ESTATUS" )
  private long idEstatus;

  //Constructores
  public Nota() {

  }

  public Nota( String nombre, String titulo, String contenido, long idEstatus ) {
    super();
    this.nombre = nombre;
    this.titulo = titulo;
    this.contenido = contenido;
    this.idEstatus = idEstatus;
  }

  public int getIdNota() {
    return idNota;
  }

  public void setIdNota(int id) {
    this.idNota = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getContenido() {
    return contenido;
  }

  public void setContenido(String contenido) {
    this.contenido = contenido;
  }

  public long getIdEstatus() {
    return idEstatus;
  }

  public void setIdEstatus(long idEstus) {
    this.idEstatus = idEstus;
  }

  
}
