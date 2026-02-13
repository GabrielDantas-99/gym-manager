package com.gym.api.gym_management_api.infraestructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.gym.api.gym_management_api.domain.model.Vinculo;
import com.gym.api.gym_management_api.domain.repository.VinculoRepository;
import com.gym.api.gym_management_api.infraestructure.persistence.mapper.VinculoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class VinculoRepositoryImpl implements VinculoRepository {

  private final VinculoJpaRepository jpaRepository;
  private final VinculoMapper mapper;

  @Override
  public Vinculo save(Vinculo vinculo) {
    var entity = mapper.toEntity(vinculo);
    var savedEntity = jpaRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public Optional<Vinculo> findById(Long id) {
    return jpaRepository.findById(id)
        .map(mapper::toDomain);
  }

  @Override
  public Optional<Vinculo> findByUsuarioIdAndAcademiaId(Long usuarioId, Long academiaId) {
    return jpaRepository.findByUsuarioIdAndAcademiaId(usuarioId, academiaId)
        .map(mapper::toDomain);
  }

  @Override
  public List<Vinculo> findByAcademiaId(Long academiaId) {
    return jpaRepository.findByAcademiaId(academiaId).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<Vinculo> findByUsuarioId(Long usuarioId) {
    return jpaRepository.findByUsuarioId(usuarioId).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<Vinculo> findByPersonalId(Long personalId) {
    return jpaRepository.findByPersonalId(personalId).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }

  @Override
  public boolean existsByUsuarioIdAndAcademiaId(Long usuarioId, Long academiaId) {
    return jpaRepository.existsByUsuarioIdAndAcademiaId(usuarioId, academiaId);
  }
}
