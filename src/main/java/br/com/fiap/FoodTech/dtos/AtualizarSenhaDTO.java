package br.com.fiap.FoodTech.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AtualizarSenhaDTO(

        @NotNull(message = "O ID do usuário é obrigatório")
        @Positive(message = "O ID do usuário deve ser maior que zero")
        Long usuarioId,

        @NotBlank(message = "A senha antiga é obrigatória")
        String senhaAntiga,

        @NotBlank(message = "A nova senha é obrigatória")
        @Size(min = 8, message = "A nova senha deve ter no mínimo 8 caracteres")
        String novaSenha
) {}
