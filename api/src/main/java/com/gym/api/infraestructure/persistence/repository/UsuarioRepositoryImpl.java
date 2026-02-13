package com.gym.api.infraestructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gym.api.domain.model.Usuario;
import com.gym.api.domain.repository.UsuarioRepository;
import com.gym.api.infraestructure.persistence.mapper.UsuarioMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

  private final UsuarioJpaRepository jpaRepository;
  private final UsuarioMapper mapper;

  @Override
  public Usuario save(Usuario usuario) {
    var entity = mapper.toEntity(usuario);
    var savedEntity = jpaRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public Optional<Usuario> findById(Long id) {
    return jpaRepository.findById(id).map(mapper::toDomain);
  }

  @Override
  public Optional<Usuario> findByEmail(String email) {
    return jpaRepository.findByEmail(email).map(mapper::toDomain);
  }

  @Override
  public List<Usuario> findAll() {
    return jpaRepository.findAll().stream()
        .map(mapper::toDomain)
        .toList();
  }

  @Override
  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }

  @Override
  public boolean existsByEmail(String email) {
    return jpaRepository.existsByEmail(email);
  }

}
