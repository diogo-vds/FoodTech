package br.com.fiap.FoodTech.dtos;

import br.com.fiap.FoodTech.entities.TipoUsuario;
import br.com.fiap.FoodTech.entities.UF;
import jakarta.validation.constraints.*;

public record UsuarioCreateDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        @Size(max = 150, message = "E-mail deve ter no máximo 150 caracteres")
        String email,

        @NotBlank(message = "Login é obrigatório")
        @Size(min = 3, max = 50, message = "Login deve ter entre 3 e 50 caracteres")
        String login,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, max = 100, message = "Senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotBlank(message = "Logradouro é obrigatório")
        String logradouro,

        @NotNull(message = "Número é obrigatório")
        @Positive(message = "Número deve ser positivo")
        Long numero,

        @NotBlank(message = "Bairro é obrigatório")
        String bairro,

        @NotBlank(message = "Cidade é obrigatória")
        String cidade,

        @NotNull(message = "UF é obrigatória")
        UF uf,

        @NotBlank(message = "CEP é obrigatório")
        @Pattern(
                regexp = "^\\d+$",
                message = "CEP deve conter apenas números"
        )
        String cep,

        @NotNull(message = "Tipo de usuário é obrigatório")
        TipoUsuario tipoUsuario

) {}