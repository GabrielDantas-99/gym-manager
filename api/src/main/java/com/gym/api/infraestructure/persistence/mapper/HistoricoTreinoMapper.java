package com.gym.api.infraestructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.gym.api.domain.model.HistoricoTreino;
import com.gym.api.infraestructure.persistence.entity.HistoricoTreinoEntity;

@Component
public class HistoricoTreinoMapper {

  public HistoricoTreinoEntity toEntity(HistoricoTreino historicoTreino) {
    if (historicoTreino == null) {
      return null;
    }

    return HistoricoTreinoEntity.builder()
        .id(historicoTreino.getId())
        .fichaTreinoId(historicoTreino.getFichaTreinoId())
        .alunoId(historicoTreino.getAlunoId())
        .dataConclusao(historicoTreino.getDataConclusao())
        .build();
  }

  public HistoricoTreino toDomain(HistoricoTreinoEntity entity) {
    if (entity == null) {
      return null;
    }

    return HistoricoTreino.builder()
        .id(entity.getId())
        .fichaTreinoId(entity.getFichaTreinoId())
        .alunoId(entity.getAlunoId())
        .dataConclusao(entity.getDataConclusao())
        .build();
  }
}