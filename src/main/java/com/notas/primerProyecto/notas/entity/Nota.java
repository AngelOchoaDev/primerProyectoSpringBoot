package com.notas.primerProyecto.notas.entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity(name = "nota")
public class Nota implements Serializable {

  //Columnas de la tabla
  @GeneratedValue( strategy = GenerationType.AUTO )
  @Id
  @Column( name = "ID_NOTA" )
  private int id;
  
  @Column( name = "NOMBRE" )
  private String nombre;

  @Column( name = "TITULO" )
  private String titulo;

  @Column( name = "CONTENIDO" )
  private String contenido;

  //Constructores
  public Nota() {

  }

  public Nota( String nombre, String titulo, String contenido ) {
    super();
    this.nombre = nombre;
    this.titulo = titulo;
    this.contenido = contenido;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  
}
