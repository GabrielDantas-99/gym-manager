package com.gym.api.infraestructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.gym.api.domain.model.Mensalidade;
import com.gym.api.infraestructure.persistence.entity.MensalidadeEntity;

@Component
public class MensalidadeMapper {

  public MensalidadeEntity toEntity(Mensalidade mensalidade) {
    if (mensalidade == null) {
      return null;
    }

    return MensalidadeEntity.builder()
        .id(mensalidade.getId())
        .alunoId(mensalidade.getAlunoId())
        .valor(mensalidade.getValor())
        .mesReferencia(mensalidade.getMesReferencia())
        .dataVencimento(mensalidade.getDataVencimento())
        .status(mensalidade.getStatus())
        .dataPagamento(mensalidade.getDataPagamento())
        .dataCriacao(mensalidade.getDataCriacao())
        .build();
  }

  public Mensalidade toDomain(MensalidadeEntity entity) {
    if (entity == null) {
      return null;
    }

    return Mensalidade.builder()
        .id(entity.getId())
        .alunoId(entity.getAlunoId())
        .valor(entity.getValor())
        .mesReferencia(entity.getMesReferencia())
        .dataVencimento(entity.getDataVencimento())
        .status(entity.getStatus())
        .dataPagamento(entity.getDataPagamento())
        .dataCriacao(entity.getDataCriacao())
        .build();
  }
}
