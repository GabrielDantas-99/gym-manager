package com.gym.api.infraestructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.gym.api.domain.model.Despesa;
import com.gym.api.domain.model.TipoDespesa;
import com.gym.api.domain.repository.DespesaRepository;
import com.gym.api.infraestructure.persistence.mapper.DespesaMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DespesaRepositoryImpl implements DespesaRepository {

  private final DespesaJpaRepository jpaRepository;
  private final DespesaMapper mapper;

  @Override
  public Despesa save(Despesa despesa) {
    var entity = mapper.toEntity(despesa);
    var savedEntity = jpaRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public Optional<Despesa> findById(Long id) {
    return jpaRepository.findById(id)
        .map(mapper::toDomain);
  }

  @Override
  public List<Despesa> findByAcademiaId(Long academiaId) {
    return jpaRepository.findByAcademiaIdOrderByDataDespesaDesc(academiaId).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<Despesa> findByTipo(TipoDespesa tipo) {
    return jpaRepository.findByTipo(tipo).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<Despesa> findAll() {
    return jpaRepository.findAll().stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }
}
