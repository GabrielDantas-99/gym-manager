package com.gym.api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioResponse {

  private Long id;
  private String nome;
  private Integer series;
  private String repeticoes;
  private String observacoes;
  private Integer ordem;
}
