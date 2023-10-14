package com.notas.primerProyecto.Utils;

import java.util.List;

public class ListResponse<T> {

  private Long statusCode;

  private List<T> catalog;

  private String message;

  public ListResponse() {
  }

  public Long getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Long statusCode) {
    this.statusCode = statusCode;
  }

  public List<T> getCatalog() {
    return catalog;
  }

  public void setCatalog(List<T> model) {
    this.catalog = model;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  
  
}
