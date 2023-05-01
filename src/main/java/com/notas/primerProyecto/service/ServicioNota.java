package com.notas.primerProyecto.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.notas.primerProyecto.converter.ConvertidorNota;
import com.notas.primerProyecto.entity.Nota;
import com.notas.primerProyecto.model.MNota;
import com.notas.primerProyecto.repository.NotaRepositorio;

@Service( "ServicioNota" )
public class ServicioNota {

  @Autowired // -> 'Autowired' significa que se autoconecta a los componentes
  @Qualifier( "NotaRepositorio" ) // -> Y con 'Qualifier' se sabe que bean inyectar
  private NotaRepositorio repositorioNota;

  @Autowired
  @Qualifier( "ConvertidorNota" )
  private ConvertidorNota convertidorNota;

  // Creando el Log

  public static final Log log = LogFactory.getLog( ServicioNota.class );

  //A partir de aca ya empiezan los metodos para crear/modificar/borrar campos de las tablas

  // -> Para crear un registro (insert)
  public String crear( Nota nota ) {
    try {
      repositorioNota.save( nota );
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
      temporal = repositorioNota.findByNombre( nota.getNombre() ); // Se encuentra la nota en la base de datos
      temporal.setTitulo( nota.getTitulo() );
      temporal.setContenido( nota.getContenido() );
      repositorioNota.save( temporal );
    } catch ( Exception e ) { 
      log.error( "La nota a buscar no existe: " + e.toString() );
      return "La nota a buscar no existe.";
    }
    return "Registro modificado con exito";
  }

  // -> Para borrar un registro (delete)
  public String borrar( String nombre, long id ) {

    Nota nota;

    try {
      nota = repositorioNota.findByNombreAndId( nombre, id );
      repositorioNota.delete( nota );
    } catch ( Exception e ) {
      log.error( "No se encontró el registro: " + e.toString() );
      return "No se encontro el registro.";
    }
    return "El registro se elimino con exito.";
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

    Nota nota;
    try {
      nota = repositorioNota.findByNombreAndTitulo( nombre, titulo );
    } catch (Exception e) {
      log.error( "No se encontro una nota con los datos proporcionados: " + e.toString() );
      return null;
    }
    log.info( "Se encontro la nota: " + nota.getId() );
    return new MNota( nota );
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
     * 
     * Esto esta obsoleto, ya que si mi repositorio me retornanara una lista de
     * entidades enorme este metodo saturaria la query, por ello se utiliza la paginacion
     */

    List<MNota> notas;
    try {
      notas = convertidorNota.convertirLista( repositorioNota.findAll() );
    } catch (Exception e) {
      log.error( "Hubo un error en la peticion a la base de datos: " + e.toString() );
      return null;
    }
    log.info( "La peticion a la base de datos retorno " + notas.size() + " nota(s)." );
    return notas;
  }

  // -> Para obtener un conjunto de registros que compartan el mismo nombre (SELECT * FROM notas WHERE titulo = titulo)
  public List<MNota> obtenerNotasPorTitulo( String titulo, Pageable pageable ) {

    /* 
     * Anotaciones:
     * 
     * De manera similar a los metodos anteriores, se le pide al repositorio
     * que haga una peticion a la base de datos de encontrar una lista de
     * notas que coincidan con el titulo dado, las retorne como parametro
     * a la funcion convertirLista del objeto convertidor, que este a su vez
     * toma esta lista de entidades y las convierte en una lista de modelos.
     */

    log.info( "Se buscan notas con el titulo: " + titulo );
    // if ( titulo.length() == 0 || titulo == null ) { 
    //   log.error( "Error, datos de entrada inválidos." );
    //   return null;
    // }
    List<Nota> notas;
    try {
      notas = repositorioNota.findByTitulo( titulo, pageable ).getContent();
    } catch (Exception e) {
      log.error( "Hubo un error en la peticion a la base de datos: " + e.toString() );
      return null;
    }
    if( notas.size() == 0 ) {
      log.info("No se encontraron notas en la base de datos.");
      return null;
    }
    log.info("La peticion a la base de datos retorno " + notas.size() + " nota(s)." );
    return convertidorNota.convertirLista( notas );
  }

  public List<MNota> obtenerPorPaginacion( Pageable pageable ) {
    return convertidorNota.convertirLista( repositorioNota.findAll( pageable ).getContent() );
  }
}
