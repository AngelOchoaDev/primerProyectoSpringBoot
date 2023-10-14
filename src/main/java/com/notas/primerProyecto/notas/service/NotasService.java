package com.notas.primerProyecto.notas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.notas.primerProyecto.notas.entity.Nota;

@Service
public interface NotasService {
  
  Nota getNotaById(long id);

  Nota saveNota(Nota nota);
  
  List<Nota> getNotas();

  List<Nota> getNotasByNombre(String nombre);

  List<Nota> getNotasByTitulo(String titulo);

  //Nota borrarNota(long id);

}
