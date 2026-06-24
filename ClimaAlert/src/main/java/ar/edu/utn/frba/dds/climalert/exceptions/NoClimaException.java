package ar.edu.utn.frba.dds.climalert.exceptions;

public class NoClimaException extends RuntimeException{
  public NoClimaException(String message, Throwable cause) {
    super(message, cause);
  }

  public NoClimaException(String message) {
    super(message);
  }
}
