package com.gym.api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

  private Long totalAlunos;
  private Long totalPersonais;
  private BigDecimal receitaMensal;
  private BigDecimal totalDespesas;
  private BigDecimal receitaLiquida;
  private Long mensalidadesPendentes;
  private Long mensalidadesPagas;
}
