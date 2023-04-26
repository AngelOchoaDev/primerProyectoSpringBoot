package com.notas.primerProyecto.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notas.primerProyecto.entity.Nota;

@Repository( "NotaRepositorio" )

// extends JpaRepository<Nota, Serializable>
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
   * 
   * Para paginación, JpaRepository ya extiende de la interface 'PagingAndSortingRepository'
   * lo cual nos brinda de manera automatica metodos como findAll( Pageable pageable ) que recibe
   * un parametro de tipo Pageable y retorna una Page, la cual se utiliza en los servicios
   * para obtener la lista de notas que pertenecen a esa pagina
   */
  
  //Query para encontrar por nombre
  public abstract Nota findByNombre( String nombre );

  //Query para encontrar por nombre y por titulo
  public abstract Nota findByNombreAndTitulo( String nombre, String titulo );

  //Query para encontrar notas por nombre e id
  public abstract Nota findByNombreAndId( String nombre, long id );

  //Query para encontrar varias notas con un titulo
  public abstract Page<Nota> findByTitulo( String titulo, Pageable pageable );
}
