package com.gym.api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import com.gym.api.domain.model.DiaSemana;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FichaTreinoResponse {

  private Long id;
  private Long alunoId;
  private String nomeAluno;
  private Long personalId;
  private String nomePersonal;
  private DiaSemana diaSemana;
  private String grupoMuscular;
  private Boolean ativa;
  private List<ExercicioResponse> exercicios;
  private LocalDateTime dataCriacao;
}
