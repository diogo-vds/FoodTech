package br.com.fiap.FoodTech.repositories;

import br.com.fiap.FoodTech.dtos.UsuarioUpdateDTO;
import br.com.fiap.FoodTech.entities.Usuario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImp implements UsuarioRepository {

    private final JdbcClient jdbcClient;

    public UsuarioRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios WHERE id = :id")
                .param("id", id)
                .query(Usuario.class)
                .optional();
    }

    public List<Usuario> findUsersByName(String nome) {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios WHERE LOWER(nome) LIKE LOWER(:nome)")
                .param("nome", "%" + nome + "%")
                .query(Usuario.class)
                .list();
    }

    @Override
    public Integer save(Usuario usuario) {

        return this.jdbcClient
                .sql("INSERT INTO usuarios (nome, email, login, senha, logradouro, numero, bairro, cidade, uf, cep, data_alteracao, tipo_usuario) " +
                        "VALUES (:nome, :email, :login, :senha, :logradouro, :numero, :bairro, :cidade, :uf, :cep, :data_alteracao, :tipo_usuario)")
                .param("nome", usuario.getNome())
                .param("email", usuario.getEmail())
                .param("login", usuario.getLogin())
                .param("senha", usuario.getSenha())
                .param("logradouro", usuario.getLogradouro())
                .param("numero", usuario.getNumero())
                .param("bairro", usuario.getBairro())
                .param("cidade", usuario.getCidade())
                .param("uf", usuario.getUf().getSigla())
                .param("cep", usuario.getCep())
                .param("data_alteracao", LocalDateTime.now())
                .param("tipo_usuario", usuario.getTipoUsuario().getDescricao())
                .update();
    }

    @Override
    public Integer update(UsuarioUpdateDTO usuario, Long id) {
        return this.jdbcClient
                .sql("UPDATE usuarios SET nome = :nome, email = :email, login = :login, senha = :senha, logradouro = :logradouro, numero = :numero, " +
                        "bairro = :bairro, cidade = :cidade, uf = :uf, cep = :cep, data_alteracao = :data_alteracao," +
                        " tipo_usuario = :tipo_usuario WHERE id = :id")
                .param("nome", usuario.nome())
                .param("email", usuario.email())
                .param("login", usuario.login())
                .param("senha", usuario.senha())
                .param("logradouro", usuario.logradouro())
                .param("numero", usuario.numero())
                .param("bairro", usuario.bairro())
                .param("cidade", usuario.cidade())
                .param("uf", usuario.uf().getSigla())
                .param("cep", usuario.cep())
                .param("data_alteracao", LocalDateTime.now())
                .param("tipo_usuario", usuario.tipoUsuario().getDescricao())
                .param("id", id)
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM usuarios WHERE id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean emailExists(String email) {
        Integer count = this.jdbcClient
                .sql("SELECT COUNT(*) FROM usuarios WHERE email = :email")
                .param("email", email)
                .query(Integer.class)
                .single();

        return count != null && count > 0;
    }



    @Override
    public List<Usuario> findAll() {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios")
                .query(Usuario.class)
                .list();
    }

    @Override
    public int updateSenha(Long id, String novaSenhaCriptografada) {
        return this.jdbcClient
                .sql("""
                UPDATE usuarios 
                SET senha = :senha, data_alteracao = NOW() 
                WHERE id = :id
            """)
                .param("senha", novaSenhaCriptografada)
                .param("id", id)
                .update();
    }

    @Override
    public Optional<Usuario> findByLogin(String login) {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios WHERE login = :login")
                .param("login", login)
                .query(Usuario.class)
                .optional();
    }
}