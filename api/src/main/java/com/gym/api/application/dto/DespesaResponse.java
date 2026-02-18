package com.gym.api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.gym.api.domain.model.TipoDespesa;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DespesaResponse {

  private Long id;
  private Long academiaId;
  private String nomeAcademia;
  private String descricao;
  private BigDecimal valor;
  private TipoDespesa tipo;
  private LocalDate dataDespesa;
}
