package br.com.fiap.FoodTech.entities;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "senha")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @EqualsAndHashCode.Include
    private Long id;

    private String nome;
    private String email;
    private String login;

    private String senha;

    private String logradouro;
    private Long numero;
    private String bairro;
    private String cidade;
    private UF uf;
    private String cep;

    private LocalDateTime dataAlteracao;
    private TipoUsuario tipoUsuario;

    public String getSenha() {
        return senha;
    }
}
