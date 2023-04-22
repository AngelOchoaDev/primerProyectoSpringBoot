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

@Service( "ServicioNota" )
public class ServicioNota {

  @Autowired // -> 'Autowired' significa inyectar un bean
  @Qualifier( "NotaRepositorio" ) // -> Y con 'Qualifier' se sabe que bean inyectar
  private NotaRepositorio repositorio;

  @Autowired
  @Qualifier( "ConvertidorNota" )
  private ConvertidorNota convertidor;

  // Creando el Log

  public static final Log log = LogFactory.getLog( ServicioNota.class );

  //A partir de aca ya empiezan los metodos para crear/modificar/borrar campos de las tablas

  // -> Para crear un registro (insert)
  public String crear( Nota nota ) {
    if( nota == null ) { 
      log.error( "Los datos recividos no son una nota" );
      return "Los datos recibidos no son una nota"; 
    }
    try {
      repositorio.save( nota );
    } catch ( Exception e ) {
      log.error( "Registro no realizado: " + e.toString() );
      return "No se pudo agregar el registro.";
    }
    log.info( "Se ha agredado el registro con exito" );
    return "Se ha agregado el registro con exito.";
  }

  // -> Para actualizar un registro (update)
  public String actualizar( Nota nota ) {
    //-> Comprobacion de erorres en la nota recibida
    if ( nota == null || nota.getNombre().length() == 0 || nota.getTitulo().length() == 0 || nota.getContenido().length()== 0 ) {
      log.error( "Nota no válida." );
      return "Nota no válida";
    }

    Nota temporal;
    try {
      temporal = repositorio.findByNombre( nota.getNombre() ); // Se encuentra la nota en la base de datos
      temporal.setTitulo( nota.getTitulo() );
      temporal.setContenido( nota.getContenido() );
      repositorio.save( temporal );
    } catch ( Exception e ) { 
      log.error( "La nota a buscar no existe: " + e.toString() );
      return "La nota a buscar no existe.";
    }
    return "Registro modificado con exito";
  }

  // -> Para borrar un registro (delete)
  public String borrar( String nombre, long id ) {
    if ( nombre == null || id == 0 ) {
      log.error( "Los valores ingresados son incorrectos." );
      return "Los valores ingresados son incorrectos";
    }

    Nota nota;
    try {
      nota = repositorio.findByNombreAndId( nombre, id );
      repositorio.delete( nota );
    } catch ( Exception e ) {
      log.error( "No se encontró el registro: " + e.toString() );
      return "No se encontro el registro.";
    }
    return "El registro se elimino con exito.";
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

    List<MNota> notas = convertidor.convertirLista( repositorio.findAll() );
    log.info( "La petición a la base de datos retornó " + notas.size() + " nota(s)." );
    return notas;
  }

  public MNota obtenerPorNombreYTitulo( String nombre, String titulo ) {

    /*
     * Anotaciones:
     * 
     * En esta función se le pide al repositorio que busque en la base
     * de datos en la tabla llamada 'notas' la nota correspondiente al
     * nombre y al titulo dado y la retorne como parametro del constructor
     * de MNota para que convierta esa Nota en una MNota (De entidad a modelo).
     * 
     */

    if(nombre.length() == 0 || titulo.length() == 0 ) {
      log.error( "Datos de entrada invalidos." );
      return null;
    }
    Nota nota;
    try {
      nota = repositorio.findByNombreAndTitulo( nombre, titulo );
    } catch (Exception e) {
      log.error( "No se encontró una nota con los datos proporcionados: " + e.toString() );
      return null;
    }
    log.info( "Se encontro la nota: " + nota.toString() );
    return new MNota( nota );
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

    if ( titulo.length() == 0 || titulo == null ) { 
      log.error( "Error, datos de entrada inválidos." );
      return null;
    }
    return convertidor.convertirLista(repositorio.findByTitulo(titulo));
  }
}
