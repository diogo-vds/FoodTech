package br.com.fiap.FoodTech.repositories;

import br.com.fiap.FoodTech.entities.Usuario;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.time.LocalDateTime;

public class SenhaRepositoryImp implements SenhaRepository {

    private final JdbcClient jdbcClient;

    public SenhaRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Integer update(Usuario usuario, Long id) {
        return this.jdbcClient.sql("UPDATE usuarios SET senha = :senha,  data_alteracao = :data_alteracao WHERE id = :id")
                .param("senha", usuario.getSenha())
                .param("data_alteracao", LocalDateTime.now())
                .param("id", id)
                .update();
    }
}
