package com.gym.api.gym_management_api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vinculo {

  private Long id;
  private Long usuarioId;
  private Long academiaId;
  private Long personalId;
  private Boolean ativo;
  private LocalDateTime dataVinculo;
  private LocalDateTime dataAtualizacao;

  public void ativar() {
    this.ativo = true;
  }

  public void inativar() {
    this.ativo = false;
  }
}
