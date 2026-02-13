package com.gym.api.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gym.api.domain.model.Usuario;

public interface UsuarioRepository {

  Usuario save(Usuario usuario);

  Optional<Usuario> findById(Long id);

  Optional<Usuario> findByEmail(String email);

  List<Usuario> findAll();

  void deleteById(Long id);

  boolean existsByEmail(String email);
}
