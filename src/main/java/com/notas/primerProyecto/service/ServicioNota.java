package com.notas.primerProyecto.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.notas.primerProyecto.converter.ConvertidorNota;
import com.notas.primerProyecto.entity.Nota;
import com.notas.primerProyecto.model.MNota;
import com.notas.primerProyecto.repository.NotaRepositorio;

@Service("ServicioNota")
public class ServicioNota {

  @Autowired // -> 'Autowired' significa inyectar un bean
  @Qualifier("NotaRepositorio") // -> Y con 'Qualifier' se sabe que bean inyectar
  private NotaRepositorio repositorio;

  @Autowired
  @Qualifier("ConvertidorNota")
  private ConvertidorNota convertidor;

  // Creando el Log

  public static final Log log = LogFactory.getLog(ServicioNota.class);

  //A partir de aca ya empiezan los metodos para crear/modificar/borrar campos de las tablas

  // -> Para crear un registro (insert)
  public String crear(Nota nota) {
    try {
      repositorio.save(nota);
      log.info("Se ha agredado el registro con exito");
      return "Se ha agregado el registro con exito.";
    } catch (Exception e) {
      log.error("No se ha agregado el registro");
      return "No se pudo agregar el registro. Error: " + e.toString();
    }
  }

  // -> Para actualizar un registro (update)
  public String actualizar(Nota nota) {
    try {
      Nota temporal = repositorio.findByNombre(nota.getNombre()); // Se encuentra la nota en la base de datos
      temporal.setTitulo(nota.getTitulo());
      temporal.setContenido(nota.getContenido());
      repositorio.save(temporal);
      return "Registro modificado con exito";
    } catch (Exception e) {
      return "No se pudo actualizar el registro. Error: " + e.toString();
    }
  }

  // -> Para borrar un registro (delete)
  public String borrar(String nombre, long id) {
    try {
      Nota nota = repositorio.findByNombreAndId(nombre, id);
      repositorio.delete(nota);
      return "El registro se elimino con exito.";
    } catch (Exception e) {
      return "No se encontro el registro.";
    }
  }

  // -> Para obtener registros (select)
  public List<MNota> obtenerTodasLasNotas() {

    /*
     * Anotaciones:
     * 
     * El metodo 'repositorio.findAll()' obtiene todos los registros de la base
     * de datos a traves de una consulta y las retorna en instancias de la clase 'Nota'.
     * 
     * Lo que hace el convertidor es recibir como parametro esa lista de instancias en forma
     * de entidades de la clase 'Nota' y las convierte a modelos de la clase 'MNota'
     */

    return convertidor.convertirLista(repositorio.findAll());
  }

  public MNota obtenerPorNombreYTitulo(String nombre, String titulo) {

    /*
     * Anotaciones:
     * 
     * En esta función se le pide al repositorio que busque en la base
     * de datos en la tabla llamada 'notas' la nota correspondiente al
     * nombre y al titulo dado y la retorne como parametro del constructor
     * de MNota para que convierta esa Nota en una MNota (De entidad a modelo).
     * 
     */

    return new MNota(repositorio.findByNombreAndTitulo(nombre, titulo));
  }

  public List<MNota> obtenerNotasPorTitulo(String titulo) {

    /* 
     * Anotaciones:
     * 
     * De manera similar a los metodos anteriores, se le pide al repositorio
     * que haga una peticion a la base de datos de encontrar una lista de
     * notas que coincidan con el titulo dado, las retorne como parametro
     * a la funcion convertirLista del objeto convertidor, que este a su vez
     * toma esta lista de entidades y las convierte en una lista de modelos.
     */

    return convertidor.convertirLista(repositorio.findByTitulo(titulo));
  }
}
