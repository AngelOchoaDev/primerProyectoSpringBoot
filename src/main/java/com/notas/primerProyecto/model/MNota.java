package com.notas.primerProyecto.model;

import com.notas.primerProyecto.entity.Nota;

public class MNota {

  //Constructores
  public MNota( long id, String nombre, String titulo, String contenido ) {
    super();
    this.id = id;
    this.nombre = nombre;
    this.titulo = titulo;
    this.contenido = contenido;
  }

  public MNota( Nota nota ) {
    this.id = nota.getId();
    this.nombre = nota.getNombre();
    this.titulo = nota.getTitulo();
    this.contenido = nota.getContenido();
  }

  public MNota() {
    
  }

  //Propiedades del modelo
  private long id;
  private String nombre;
  private String titulo;
  private String contenido;

  //Metodos get y set
  public long getId() { return id; }
  public String getNombre() { return nombre; }
  public String getTitulo() { return titulo; }
  public String getContenido() { return contenido; }

  public void setId( long id ) { this.id = id; }
  public void setNombre( String nombre ) { this.nombre = nombre; }
  public void setTitulo( String titulo ) { this.titulo = titulo; }
  public void setContenido( String contenido ) { this.contenido = contenido; }
}
