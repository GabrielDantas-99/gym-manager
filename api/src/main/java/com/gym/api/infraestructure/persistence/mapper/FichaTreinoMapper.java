package com.gym.api.infraestructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.gym.api.domain.model.FichaTreino;
import com.gym.api.infraestructure.persistence.entity.FichaTreinoEntity;

@Component
public class FichaTreinoMapper {

  public FichaTreinoEntity toEntity(FichaTreino fichaTreino) {
    if (fichaTreino == null) {
      return null;
    }

    return FichaTreinoEntity.builder()
        .id(fichaTreino.getId())
        .alunoId(fichaTreino.getAlunoId())
        .personalId(fichaTreino.getPersonalId())
        .diaSemana(fichaTreino.getDiaSemana())
        .grupoMuscular(fichaTreino.getGrupoMuscular())
        .ativa(fichaTreino.getAtiva())
        .dataCriacao(fichaTreino.getDataCriacao())
        .dataAtualizacao(fichaTreino.getDataAtualizacao())
        .build();
  }

  public FichaTreino toDomain(FichaTreinoEntity entity) {
    if (entity == null) {
      return null;
    }

    return FichaTreino.builder()
        .id(entity.getId())
        .alunoId(entity.getAlunoId())
        .personalId(entity.getPersonalId())
        .diaSemana(entity.getDiaSemana())
        .grupoMuscular(entity.getGrupoMuscular())
        .ativa(entity.getAtiva())
        .dataCriacao(entity.getDataCriacao())
        .dataAtualizacao(entity.getDataAtualizacao())
        .build();
  }
}
