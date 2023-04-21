package com.notas.primerProyecto.entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
public class Nota implements Serializable {

  //Constructores
  public Nota() {
    
  }

  public Nota( String nombre, String titulo, String contenido) {
    super();
    this.nombre = nombre;
    this.titulo = titulo;
    this.contenido = contenido;
  }

  //Columnas de la tabla
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  @Column(name = "ID_NOTA")
  private int id;

  @Column(name = "NOMBRE",unique = true, nullable = false)
  private String nombre;
  
  @Column(name = "TITULO", nullable = false)
  private String titulo;

  @Column(name = "CONTENIDO", nullable = false)
  private String contenido;

  //Metodos get y set
  public int getId() { return id; }
  public String getNombre() { return nombre; }
  public String getTitulo() { return titulo; }
  public String getContenido() { return contenido; }

  public void setId(int id) { this.id = id; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public void setTitulo(String titulo) { this.titulo = titulo; }
  public void setContenido(String contenido) { this.contenido = contenido; }
}
