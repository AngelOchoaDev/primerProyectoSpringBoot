package com.notas.primerProyecto.Utils;

public class Response {

  private Long statusCode;

  private String message;

  public Response() {
  }

  public Long getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Long statusCode) {
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  
  
}
