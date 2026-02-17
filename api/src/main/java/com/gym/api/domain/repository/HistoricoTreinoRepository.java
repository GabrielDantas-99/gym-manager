package com.gym.api.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gym.api.domain.model.HistoricoTreino;

public interface HistoricoTreinoRepository {

  HistoricoTreino save(HistoricoTreino historicoTreino);

  Optional<HistoricoTreino> findById(Long id);

  List<HistoricoTreino> findByAlunoId(Long alunoId);

  List<HistoricoTreino> findByFichaTreinoId(Long fichaTreinoId);
}
