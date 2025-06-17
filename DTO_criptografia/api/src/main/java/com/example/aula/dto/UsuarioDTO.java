package com.example.aula.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "Deve ser um e-mail valido.")
    private String email;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 3, message = "A senha deve ter no mínimo 3 caracteres.")
    private String senha;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public @NotBlank(message = "O nome é obrigatório.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório.") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "E-mail é obrigatório") @Email(message = "Deve ser um e-mail valido.") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "E-mail é obrigatório") @Email(message = "Deve ser um e-mail valido.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Senha é obrigatória.") @Size(min = 3, message = "A senha deve ter no mínimo 3 caracteres.") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "Senha é obrigatória.") @Size(min = 3, message = "A senha deve ter no mínimo 3 caracteres.") String senha) {
        this.senha = senha;
    }
}
