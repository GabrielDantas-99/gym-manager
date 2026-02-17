package com.gym.api.infraestructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.gym.api.domain.model.FichaTreino;
import com.gym.api.domain.repository.FichaTreinoRepository;
import com.gym.api.infraestructure.persistence.mapper.FichaTreinoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FichaTreinoRepositoryImpl implements FichaTreinoRepository {

  private final FichaTreinoJpaRepository jpaRepository;
  private final FichaTreinoMapper mapper;

  @Override
  public FichaTreino save(FichaTreino fichaTreino) {
    var entity = mapper.toEntity(fichaTreino);
    var savedEntity = jpaRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public Optional<FichaTreino> findById(Long id) {
    return jpaRepository.findById(id)
        .map(mapper::toDomain);
  }

  @Override
  public List<FichaTreino> findByAlunoId(Long alunoId) {
    return jpaRepository.findByAlunoId(alunoId).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<FichaTreino> findByPersonalId(Long personalId) {
    return jpaRepository.findByPersonalId(personalId).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }
}
