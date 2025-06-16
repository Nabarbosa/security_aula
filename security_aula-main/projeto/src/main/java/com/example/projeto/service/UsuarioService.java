package com.example.projeto.service;

import com.example.projeto.dto.UsuarioDTO;
import com.example.projeto.dto.UsuarioResponseDTO;
import com.example.projeto.model.UsuarioEntity;
import com.example.projeto.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository ususarioRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository ususarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.ususarioRepository = ususarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UsuarioEntity salvarUsusario(UsuarioDTO dto){
        ususarioRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> {throw new IllegalArgumentException("E-mail já cadastrado"); });

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

        return ususarioRepository.save(usuario);
    }

    public List<UsuarioResponseDTO> listarTodosUsuarios() {
        return ususarioRepository
                .findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public UsuarioResponseDTO toDTO(UsuarioEntity usuario) {
        return new UsuarioResponseDTO(usuario.getNome(), usuario.getEmail());
    }

    public UsuarioEntity atualizar(String email, UsuarioDTO dto) {
        UsuarioEntity usuario = ususarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

        return ususarioRepository.save(usuario);
    }

    public void excluir(String email) {
        UsuarioEntity usuario = ususarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        ususarioRepository.delete(usuario);
    }
}
