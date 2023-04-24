package com.notas.primerProyecto.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notas.primerProyecto.entity.Nota;

@Repository( "NotaRepositorio" )
public interface NotaRepositorio extends JpaRepository<Nota, Serializable> {

  /*
   * Anotaciones:
   * 
   * La interfaz JpaRepository ya tiene metodos como ...
   * 
   * save: para guardar un regstro
   * delete: para borrar un registro
   * 
   * y otros.
   * 
   * Los metodos abstractos definidos en la siguiente seccion son
   * acciones personalizadas y especificas a nuestro servicio,
   * con ellas podemos implementar funcionalidades ya que depende de 
   * nuestras tablas y la forma en que organizamos informacion el como
   * se va a comportar precisamente nuestro servicio.
   */
  
  //Query para encontrar por nombre
  public abstract Nota findByNombre( String nombre );

  //Query para encontrar por titulo
  public abstract List<Nota> findByTitulo( String titulo );

  //Query para encontrar por nombre y por titulo
  public abstract Nota findByNombreAndTitulo( String nombre, String titulo );

  public abstract Nota findByNombreAndId( String nombre, long id );
}
