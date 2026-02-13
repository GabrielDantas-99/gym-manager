package com.gym.api.gym_management_api.infraestructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.gym.api.gym_management_api.domain.model.Academia;
import com.gym.api.gym_management_api.domain.repository.AcademiaRepository;
import com.gym.api.gym_management_api.infraestructure.persistence.mapper.AcademiaMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AcademiaRepositoryImpl implements AcademiaRepository {

  private final AcademiaJpaRepository jpaRepository;
  private final AcademiaMapper mapper;

  @Override
  public Academia save(Academia academia) {
    var entity = mapper.toEntity(academia);
    var savedEntity = jpaRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public Optional<Academia> findById(Long id) {
    return jpaRepository.findById(id)
        .map(mapper::toDomain);
  }

  @Override
  public List<Academia> findAll() {
    return jpaRepository.findAll().stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<Academia> findByAdminId(Long adminId) {
    return jpaRepository.findByAdminId(adminId).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }
}
