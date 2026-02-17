package com.gym.api.infraestructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.domain.model.Exercicio;
import com.gym.api.domain.repository.ExercicioRepository;
import com.gym.api.infraestructure.persistence.mapper.ExercicioMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExercicioRepositoryImpl implements ExercicioRepository {

  private final ExercicioJpaRepository jpaRepository;
  private final ExercicioMapper mapper;

  @Override
  public Exercicio save(Exercicio exercicio) {
    var entity = mapper.toEntity(exercicio);
    var savedEntity = jpaRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public Optional<Exercicio> findById(Long id) {
    return jpaRepository.findById(id)
        .map(mapper::toDomain);
  }

  @Override
  public List<Exercicio> findByFichaTreinoId(Long fichaTreinoId) {
    return jpaRepository.findByFichaTreinoIdOrderByOrdemAsc(fichaTreinoId).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }

  @Override
  @Transactional
  public void deleteByFichaTreinoId(Long fichaTreinoId) {
    jpaRepository.deleteByFichaTreinoId(fichaTreinoId);
  }
}
