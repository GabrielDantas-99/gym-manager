package com.gym.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Despesa {

  private Long id;
  private Long academiaId;
  private String descricao;
  private BigDecimal valor;
  private TipoDespesa tipo;
  private LocalDate dataDespesa;
  private LocalDateTime dataCriacao;
}
