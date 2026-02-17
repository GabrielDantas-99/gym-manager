package com.gym.api.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gym.api.domain.model.FichaTreino;

public interface FichaTreinoRepository {

  FichaTreino save(FichaTreino fichaTreino);

  Optional<FichaTreino> findById(Long id);

  List<FichaTreino> findByAlunoId(Long alunoId);

  List<FichaTreino> findByPersonalId(Long personalId);

  void deleteById(Long id);
}
