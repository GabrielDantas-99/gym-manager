package com.gym.api.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gym.api.domain.model.Exercicio;

public interface ExercicioRepository {

  Exercicio save(Exercicio exercicio);

  Optional<Exercicio> findById(Long id);

  List<Exercicio> findByFichaTreinoId(Long fichaTreinoId);

  void deleteById(Long id);

  void deleteByFichaTreinoId(Long fichaTreinoId);
}
