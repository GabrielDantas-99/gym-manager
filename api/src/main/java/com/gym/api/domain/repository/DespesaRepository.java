package com.gym.api.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gym.api.domain.model.Despesa;
import com.gym.api.domain.model.TipoDespesa;

public interface DespesaRepository {

  Despesa save(Despesa despesa);

  Optional<Despesa> findById(Long id);

  List<Despesa> findByAcademiaId(Long academiaId);

  List<Despesa> findByTipo(TipoDespesa tipo);

  List<Despesa> findAll();

  void deleteById(Long id);
}
