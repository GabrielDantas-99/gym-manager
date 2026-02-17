package com.gym.api.infraestructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.gym.api.domain.model.HistoricoTreino;
import com.gym.api.domain.repository.HistoricoTreinoRepository;
import com.gym.api.infraestructure.persistence.mapper.HistoricoTreinoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class HistoricoTreinoRepositoryImpl implements HistoricoTreinoRepository {

  private final HistoricoTreinoJpaRepository jpaRepository;
  private final HistoricoTreinoMapper mapper;

  @Override
  public HistoricoTreino save(HistoricoTreino historicoTreino) {
    var entity = mapper.toEntity(historicoTreino);
    var savedEntity = jpaRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public Optional<HistoricoTreino> findById(Long id) {
    return jpaRepository.findById(id)
        .map(mapper::toDomain);
  }

  @Override
  public List<HistoricoTreino> findByAlunoId(Long alunoId) {
    return jpaRepository.findByAlunoIdOrderByDataConclusaoDesc(alunoId).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<HistoricoTreino> findByFichaTreinoId(Long fichaTreinoId) {
    return jpaRepository.findByFichaTreinoIdOrderByDataConclusaoDesc(fichaTreinoId).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }
}
