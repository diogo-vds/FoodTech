package br.com.fiap.FoodTech.exceptions;

public class SenhaInvalidaException extends RuntimeException {

  public SenhaInvalidaException(String message) {
    super(message);
  }
}
