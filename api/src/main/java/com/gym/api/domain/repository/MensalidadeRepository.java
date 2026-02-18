package com.gym.api.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gym.api.domain.model.Mensalidade;
import com.gym.api.domain.model.StatusPagamento;

public interface MensalidadeRepository {

  Mensalidade save(Mensalidade mensalidade);

  Optional<Mensalidade> findById(Long id);

  List<Mensalidade> findByAlunoId(Long alunoId);

  List<Mensalidade> findByStatus(StatusPagamento status);

  List<Mensalidade> findAll();

  void deleteById(Long id);
}
