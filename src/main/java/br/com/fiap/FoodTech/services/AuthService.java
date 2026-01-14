package br.com.fiap.FoodTech.services;

import br.com.fiap.FoodTech.dtos.LoginRequestDTO;
import br.com.fiap.FoodTech.entities.Usuario;
import br.com.fiap.FoodTech.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean validaLogin(LoginRequestDTO loginDto) {

        Optional<Usuario> usuarioOpt =
                usuarioRepository.findByLogin(loginDto.login());

        if (usuarioOpt.isEmpty()) {
            return false;
        }

        Usuario usuario = usuarioOpt.get();

        return passwordEncoder.matches(
                loginDto.senha(),
                usuario.getSenha()
        );
    }
}
