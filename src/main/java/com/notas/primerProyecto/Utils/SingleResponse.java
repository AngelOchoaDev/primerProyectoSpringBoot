package com.notas.primerProyecto.Utils;

public class SingleResponse<T> {

  private Long statusCode;

  private T model;

  private String message;

  public SingleResponse() {
  }

  public Long getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Long statusCode) {
    this.statusCode = statusCode;
  }

  public T getModel() {
    return model;
  }

  public void setModel(T model) {
    this.model = model;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  
  
}
