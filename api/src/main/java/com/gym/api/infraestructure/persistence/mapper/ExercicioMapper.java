package com.gym.api.infraestructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.gym.api.domain.model.Exercicio;
import com.gym.api.infraestructure.persistence.entity.ExercicioEntity;

@Component
public class ExercicioMapper {

  public ExercicioEntity toEntity(Exercicio exercicio) {
    if (exercicio == null) {
      return null;
    }

    return ExercicioEntity.builder()
        .id(exercicio.getId())
        .fichaTreinoId(exercicio.getFichaTreinoId())
        .nome(exercicio.getNome())
        .series(exercicio.getSeries())
        .repeticoes(exercicio.getRepeticoes())
        .observacoes(exercicio.getObservacoes())
        .ordem(exercicio.getOrdem())
        .build();
  }

  public Exercicio toDomain(ExercicioEntity entity) {
    if (entity == null) {
      return null;
    }

    return Exercicio.builder()
        .id(entity.getId())
        .fichaTreinoId(entity.getFichaTreinoId())
        .nome(entity.getNome())
        .series(entity.getSeries())
        .repeticoes(entity.getRepeticoes())
        .observacoes(entity.getObservacoes())
        .ordem(entity.getOrdem())
        .build();
  }
}
