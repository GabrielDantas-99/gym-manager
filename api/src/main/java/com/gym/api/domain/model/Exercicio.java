package com.gym.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exercicio {
  private Long id;
  private Long fichaTreinoId;
  private String nome;
  private Integer series;
  private String repeticoes;
  private String observacoes;
  private Integer ordem;
}
