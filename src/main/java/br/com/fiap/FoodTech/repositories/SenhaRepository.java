package br.com.fiap.FoodTech.repositories;

import br.com.fiap.FoodTech.entities.Usuario;

public interface SenhaRepository {

    Integer update(Usuario usuario, Long id);
}
