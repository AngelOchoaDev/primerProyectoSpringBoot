package com.notas.primerProyecto.notas.model;

import com.notas.primerProyecto.notas.entity.Nota;

public record MNota(long id, String nombre, String titulo, String contenido) {

  public MNota {
    // Objects.requireNonNull(nombre, "El campo de nombre no puede estar vacio");
    // Objects.requireNonNull(titulo, "El campo de titulo no puede estar vacio");
    // Objects.requireNonNull(contenido, "El campo de contenido no puede estar vacio");
  }

  public MNota(Nota nota) {
    this(nota.getId(),nota.getNombre(),nota.getTitulo(),nota.getContenido());
  }
}