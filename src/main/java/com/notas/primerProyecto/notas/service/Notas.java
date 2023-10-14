package com.notas.primerProyecto.notas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notas.primerProyecto.notas.dao.NotasDao;
import com.notas.primerProyecto.notas.entity.Nota;

@Service
public class Notas implements NotasService {

  @Autowired
  private NotasDao notasDao;

  public Nota getNotaById(long id) {
    return this.notasDao.getNotaById(id);
  }

  public Nota saveNota(Nota nota) {
    return this.notasDao.save(nota);
  }

  public List<Nota> getNotas() {
    return this.notasDao.getNotas();
  }

  public List<Nota> getNotasByNombre(String nombre) {
    List<Nota> lista = new ArrayList<>();

    return lista;
  }

  public List<Nota> getNotasByTitulo(String titulo) {
    List<Nota> lista = new ArrayList<>();

    return lista;
  }
  
}
