package com.gym.api.infraestructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.gym.api.domain.model.Usuario;
import com.gym.api.infraestructure.persistence.entity.UsuarioEntity;

@Component
public class UsuarioMapper {

  public UsuarioEntity toEntity(Usuario usuario) {
    if (usuario == null) {
      return null;
    }

    return UsuarioEntity.builder()
        .id(usuario.getId())
        .nome(usuario.getNome())
        .email(usuario.getEmail())
        .senha(usuario.getSenha())
        .telefone(usuario.getTelefone())
        .role(usuario.getRole())
        .ativo(usuario.getAtivo())
        .dataCadastro(usuario.getDataCadastro())
        .dataAtualizacao(usuario.getDataAtualizacao())
        .build();
  }

  public Usuario toDomain(UsuarioEntity entity) {
    if (entity == null) {
      return null;
    }

    return Usuario.builder()
        .id(entity.getId())
        .nome(entity.getNome())
        .email(entity.getEmail())
        .senha(entity.getSenha())
        .telefone(entity.getTelefone())
        .role(entity.getRole())
        .ativo(entity.getAtivo())
        .dataCadastro(entity.getDataCadastro())
        .dataAtualizacao(entity.getDataAtualizacao())
        .build();
  }
}
