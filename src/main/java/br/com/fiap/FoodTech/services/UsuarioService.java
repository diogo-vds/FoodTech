package br.com.fiap.FoodTech.services;

import br.com.fiap.FoodTech.config.SecurityConfig;
import br.com.fiap.FoodTech.dtos.UsuarioCreateDTO;
import br.com.fiap.FoodTech.dtos.UsuarioUpdateDTO;
import br.com.fiap.FoodTech.entities.Usuario;
import br.com.fiap.FoodTech.exceptions.EmailAlreadyExistsException;
import br.com.fiap.FoodTech.exceptions.UserNotFoundException;
import br.com.fiap.FoodTech.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Usuario> findById(Long id) {
        return this.usuarioRepository.findById(id);
    }

    public List<Usuario> findUsersByName(String nome) {
        if (nome == null || nome.isBlank()) {
            return usuarioRepository.findAll();
        }
        return usuarioRepository.findUsersByName(nome);
    }

    public void saveUsuario(UsuarioCreateDTO dto) {

        emailExists(dto.email());
        String senhaCriptografada = passwordEncoder.encode(dto.senha());
        Usuario usuario = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .login(dto.login())
                .senha(senhaCriptografada)
                .logradouro(dto.logradouro())
                .numero(dto.numero())
                .bairro(dto.bairro())
                .cidade(dto.cidade())
                .uf(dto.uf())
                .cep(dto.cep())
                .dataAlteracao(LocalDateTime.now())
                .tipoUsuario(dto.tipoUsuario())
                .build();

        int saved = usuarioRepository.save(usuario);

        Assert.state(saved == 1, "Erro ao salvar usuário: " + usuario.getNome());
    }

    public void updateUsuario(Long id, UsuarioUpdateDTO usuario) {

        if (this.usuarioRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException(id);
        }

        var update = this.usuarioRepository.update(usuario, id);
        Assert.state(update == 1, "Erro ao atualizar usuário " + usuario.nome());
    }

    public void deleteUsuario(Long id) {
        var delete = this.usuarioRepository.delete(id);
        Assert.state(delete == 1, "Usuário nao encontrado, ID: " + id);
    }

    public void emailExists(String email) {
        if (this.usuarioRepository.emailExists(email)) {
            throw new EmailAlreadyExistsException(email);
        }
    }
}