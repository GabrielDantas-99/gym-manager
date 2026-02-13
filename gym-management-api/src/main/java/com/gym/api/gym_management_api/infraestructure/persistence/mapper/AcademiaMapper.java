package com.gym.api.gym_management_api.infraestructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.gym.api.gym_management_api.domain.model.Academia;
import com.gym.api.gym_management_api.infraestructure.persistence.entity.AcademiaEntity;

@Component
public class AcademiaMapper {
    
  public AcademiaEntity toEntity(Academia academia) {
    if (academia == null) {
        return null;
    }
    
    return AcademiaEntity.builder()
                        .id(academia.getId())
                        .nome(academia.getNome())
                        .endereco(academia.getEndereco())
                        .telefone(academia.getTelefone())
                        .adminId(academia.getAdminId())
                        .ativa(academia.getAtiva())
                        .dataCadastro(academia.getDataCadastro())
                        .dataAtualizacao(academia.getDataAtualizacao())
                        .build();
  }
  
  public Academia toDomain(AcademiaEntity entity) {
    if (entity == null) {
        return null;
    }
    
    return Academia.builder()
                  .id(entity.getId())
                  .nome(entity.getNome())
                  .endereco(entity.getEndereco())
                  .telefone(entity.getTelefone())
                  .adminId(entity.getAdminId())
                  .ativa(entity.getAtiva())
                  .dataCadastro(entity.getDataCadastro())
                  .dataAtualizacao(entity.getDataAtualizacao())
                  .build();
  }
}
