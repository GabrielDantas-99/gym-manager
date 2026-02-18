package com.gym.api.infraestructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.gym.api.domain.model.Mensalidade;
import com.gym.api.domain.model.StatusPagamento;
import com.gym.api.domain.repository.MensalidadeRepository;
import com.gym.api.infraestructure.persistence.mapper.MensalidadeMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MensalidadeRepositoryImpl implements MensalidadeRepository {

  private final MensalidadeJpaRepository jpaRepository;
  private final MensalidadeMapper mapper;

  @Override
  public Mensalidade save(Mensalidade mensalidade) {
    var entity = mapper.toEntity(mensalidade);
    var savedEntity = jpaRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public Optional<Mensalidade> findById(Long id) {
    return jpaRepository.findById(id)
        .map(mapper::toDomain);
  }

  @Override
  public List<Mensalidade> findByAlunoId(Long alunoId) {
    return jpaRepository.findByAlunoIdOrderByDataVencimentoDesc(alunoId).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<Mensalidade> findByStatus(StatusPagamento status) {
    return jpaRepository.findByStatus(status).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<Mensalidade> findAll() {
    return jpaRepository.findAll().stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }
}
