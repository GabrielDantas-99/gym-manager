package com.gym.api.gym_management_api.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gym.api.gym_management_api.domain.model.Usuario;

public interface UsuarioRepository {

  Usuario save(Usuario usuario);
  
  Optional<Usuario> findById(Long id);
  
  Optional<Usuario> findByEmail(String email);
  
  List<Usuario> findAll();
  
  void deleteById(Long id);
  
  boolean existsByEmail(String email);
}
