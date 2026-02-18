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
public class Mensalidade {

  private Long id;
  private Long alunoId;
  private BigDecimal valor;
  private String mesReferencia;
  private LocalDate dataVencimento;
  private StatusPagamento status;
  private LocalDate dataPagamento;
  private LocalDateTime dataCriacao;

  public void marcarComoPago() {
    this.status = StatusPagamento.PAGO;
    this.dataPagamento = LocalDate.now();
  }

  public boolean isPago() {
    return StatusPagamento.PAGO.equals(this.status);
  }

  public boolean isPendente() {
    return StatusPagamento.PENDENTE.equals(this.status);
  }
}
