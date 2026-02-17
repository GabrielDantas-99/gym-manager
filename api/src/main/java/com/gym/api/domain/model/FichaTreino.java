package com.gym.api.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FichaTreino {

  private Long id;
  private Long alunoId;
  private Long personalId;
  private DiaSemana diaSemana;
  private String grupoMuscular;
  private Boolean ativa;
  private LocalDateTime dataCriacao;
  private LocalDateTime dataAtualizacao;

  public void ativar() {
    this.ativa = true;
  }

  public void desativar() {
    this.ativa = false;
  }
}
