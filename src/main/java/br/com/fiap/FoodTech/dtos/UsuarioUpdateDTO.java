package br.com.fiap.FoodTech.dtos;

import br.com.fiap.FoodTech.entities.TipoUsuario;
import br.com.fiap.FoodTech.entities.UF;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateDTO(

        @NotBlank(message = "O nome não pode estar vazio")
        @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres")
        String nome,

        @NotBlank(message = "O e-mail não pode estar vazio")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "O login não pode estar vazio")
        @Size(min = 3, max = 50, message = "O login deve ter entre 3 e 50 caracteres")
        String login,

        @NotBlank(message = "A senha não pode estar vazia")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String senha,

        @NotBlank(message = "O logradouro não pode estar vazio")
        String logradouro,

        @NotNull(message = "O número da residência é obrigatório")
        Long numero,

        @NotBlank(message = "O bairro não pode estar vazio")
        String bairro,

        @NotBlank(message = "A cidade não pode estar vazia")
        String cidade,

        @NotNull(message = "O estado (UF) é obrigatório")
        UF uf,

        @NotBlank(message = "O CEP não pode estar vazio")
        @Size(min = 8, max = 9, message = "O CEP deve ter entre 8 e 9 caracteres")
        String cep,

        @NotNull(message = "O tipo do usuário é obrigatório")
        TipoUsuario tipoUsuario

) {}
