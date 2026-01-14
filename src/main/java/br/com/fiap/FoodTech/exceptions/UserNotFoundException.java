package br.com.fiap.FoodTech.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Usuário não encontrado. ID: " + id);
    }
}