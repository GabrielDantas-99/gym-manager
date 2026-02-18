package com.gym.api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.gym.api.domain.model.StatusPagamento;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MensalidadeResponse {

  private Long id;
  private Long alunoId;
  private String nomeAluno;
  private BigDecimal valor;
  private String mesReferencia;
  private LocalDate dataVencimento;
  private StatusPagamento status;
  private LocalDate dataPagamento;
}
