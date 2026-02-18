package com.gym.api.infraestructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.gym.api.domain.model.Despesa;
import com.gym.api.infraestructure.persistence.entity.DespesaEntity;

@Component
public class DespesaMapper {

  public DespesaEntity toEntity(Despesa despesa) {
    if (despesa == null) {
      return null;
    }

    return DespesaEntity.builder()
        .id(despesa.getId())
        .academiaId(despesa.getAcademiaId())
        .descricao(despesa.getDescricao())
        .valor(despesa.getValor())
        .tipo(despesa.getTipo())
        .dataDespesa(despesa.getDataDespesa())
        .dataCriacao(despesa.getDataCriacao())
        .build();
  }

  public Despesa toDomain(DespesaEntity entity) {
    if (entity == null) {
      return null;
    }

    return Despesa.builder()
        .id(entity.getId())
        .academiaId(entity.getAcademiaId())
        .descricao(entity.getDescricao())
        .valor(entity.getValor())
        .tipo(entity.getTipo())
        .dataDespesa(entity.getDataDespesa())
        .dataCriacao(entity.getDataCriacao())
        .build();
  }
}
