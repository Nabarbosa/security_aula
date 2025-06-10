package com.example.projeto.repository;

import com.example.projeto.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsusarioRepository extends JpaRepository<UsuarioEntity, Long>{
    Optional<UsuarioEntity> findByEmail(String email);
}
