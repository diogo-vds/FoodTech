package br.com.fiap.FoodTech.services;

import br.com.fiap.FoodTech.dtos.AtualizarSenhaDTO;
import br.com.fiap.FoodTech.entities.Usuario;
import br.com.fiap.FoodTech.exceptions.SenhaInvalidaException;
import br.com.fiap.FoodTech.exceptions.UserNotFoundException;
import br.com.fiap.FoodTech.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class SenhaService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public SenhaService(UsuarioRepository usuarioRepository,
                        PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void updateSenha(AtualizarSenhaDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new UserNotFoundException(dto.usuarioId()));

        validatePassword(dto.novaSenha());

        System.out.println("Senha antiga informada: " + dto.senhaAntiga());
        System.out.println("Senha atual do usuário: " + usuario.getSenha());
        if (!passwordEncoder.matches(dto.senhaAntiga(), usuario.getSenha())) {
            throw new SenhaInvalidaException("Senha antiga não confere.");
        }

        if (passwordEncoder.matches(dto.novaSenha(), usuario.getSenha())) {
            throw new SenhaInvalidaException("A nova senha deve ser diferente da senha atual.");
        }

        String senhaCriptografada = passwordEncoder.encode(dto.novaSenha());

        int updated = usuarioRepository.updateSenha(usuario.getId(), senhaCriptografada);

        if (updated != 1) {
            throw new IllegalStateException(
                    "Erro ao atualizar a senha do usuário " + usuario.getId()
            );
        }
    }

    private void validatePassword(String password) {

        if (password == null || password.isBlank()) {
            throw new SenhaInvalidaException("A senha não pode ser vazia.");
        }

        String regex =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$";

        if (!password.matches(regex)) {
            throw new SenhaInvalidaException(
                    "Senha inválida. A senha deve conter no mínimo 8 caracteres, " +
                            "com letras maiúsculas, minúsculas, números e caracteres especiais."
            );
        }
    }
}
