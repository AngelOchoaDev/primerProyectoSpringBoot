package com.notas.primerProyecto.AbstractDao;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;

public class AbstractBaseDao {

  @Autowired
  protected EntityManager entityManager;
  
}
