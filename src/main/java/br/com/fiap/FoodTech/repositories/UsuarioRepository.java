package br.com.fiap.FoodTech.repositories;

import br.com.fiap.FoodTech.dtos.UsuarioUpdateDTO;
import br.com.fiap.FoodTech.entities.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findById(Long id);

    List<Usuario> findUsersByName(String nome);

    Integer save(Usuario usuario);

    Integer update(UsuarioUpdateDTO usuario, Long id);

    Integer delete(Long id);

    boolean emailExists(String email);

    List<Usuario> findAll();

    int updateSenha(Long id, String novaSenha);

    Optional<Usuario> findByLogin(String login);
}