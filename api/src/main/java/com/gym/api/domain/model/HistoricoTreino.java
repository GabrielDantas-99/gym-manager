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
public class HistoricoTreino {

  private Long id;
  private Long fichaTreinoId;
  private Long alunoId;
  private LocalDateTime dataConclusao;

}
