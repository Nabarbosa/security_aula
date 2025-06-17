package com.example.aula.service;

import com.example.aula.dto.UsuarioDTO;
import com.example.aula.dto.UsuarioResponseDTO;
import com.example.aula.exception.EmailJaCadastradoException;
import com.example.aula.model.Usuario;
import com.example.aula.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Usuario salvarUsuario(UsuarioDTO dto) {
        usuarioRepository.findByEmail(dto.getEmail())
                .ifPresent(u ->{throw new IllegalArgumentException("E-mail já cadastrado"); });

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public List<UsuarioResponseDTO> listarTodosUsuarios() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public UsuarioResponseDTO toDTO(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getNome(), usuario.getEmail());
    }

    public Usuario atualizar(String email, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado."));

        usuario.setNome(usuario.getNome());
        usuario.setEmail(usuario.getEmail());
        usuario.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public void excluir(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }

}
