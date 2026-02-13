package com.gym.api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VinculoResponse {

  private Long id;
  private UsuarioResponse usuario;
  private Long academiaId;
  private UsuarioResponse personal;
  private Boolean ativo;
  private LocalDateTime dataVinculo;
}
