package com.gym.api.infraestructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.gym.api.domain.model.Vinculo;
import com.gym.api.infraestructure.persistence.entity.VinculoEntity;

@Component
public class VinculoMapper {

  public VinculoEntity toEntity(Vinculo vinculo) {
    if (vinculo == null) {
      return null;
    }

    return VinculoEntity.builder()
        .id(vinculo.getId())
        .usuarioId(vinculo.getUsuarioId())
        .academiaId(vinculo.getAcademiaId())
        .personalId(vinculo.getPersonalId())
        .ativo(vinculo.getAtivo())
        .dataVinculo(vinculo.getDataVinculo())
        .dataAtualizacao(vinculo.getDataAtualizacao())
        .build();
  }

  public Vinculo toDomain(VinculoEntity entity) {
    if (entity == null) {
      return null;
    }

    return Vinculo.builder()
        .id(entity.getId())
        .usuarioId(entity.getUsuarioId())
        .academiaId(entity.getAcademiaId())
        .personalId(entity.getPersonalId())
        .ativo(entity.getAtivo())
        .dataVinculo(entity.getDataVinculo())
        .dataAtualizacao(entity.getDataAtualizacao())
        .build();
  }
}
