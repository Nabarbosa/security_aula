package com.example.projeto.service;

import com.example.projeto.dto.UsuarioDTO;
import com.example.projeto.model.UsuarioEntity;
import com.example.projeto.repository.UsusarioRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private UsusarioRepository ususarioRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsusarioRepository ususarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.ususarioRepository = ususarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioEntity salvarUsusario(UsuarioDTO dto){
        if (ususarioRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha())); // Senha Criptografada

        return ususarioRepository.save(usuario);
    }

    public List<UsuarioEntity> listarTodosUsuarios() {
        return ususarioRepository.findAll();
    }
}
