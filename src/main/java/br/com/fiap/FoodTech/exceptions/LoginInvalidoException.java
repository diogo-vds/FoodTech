package br.com.fiap.FoodTech.exceptions;

public class LoginInvalidoException extends RuntimeException {
  public LoginInvalidoException() {
    super("Login ou senha inválidos.");
  }
}