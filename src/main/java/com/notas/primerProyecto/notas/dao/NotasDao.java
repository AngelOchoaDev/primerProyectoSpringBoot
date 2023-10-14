package com.notas.primerProyecto.notas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.notas.primerProyecto.notas.entity.Nota;

@Repository
public interface NotasDao extends CrudRepository<Nota, Long>, NotasExtendsDao {

  @Query("from nota where idNota =?1")
  Nota getNotaById(long id);

  @Query("from nota where idEstatus <>2 order by idNota asc")
  List<Nota> getNotas();
  
}
