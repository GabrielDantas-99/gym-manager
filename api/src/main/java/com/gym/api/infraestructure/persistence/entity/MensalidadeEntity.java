package com.gym.api.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.gym.api.domain.model.StatusPagamento;

@Entity
@Table(name = "mensalidades")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MensalidadeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "aluno_id", nullable = false)
  private Long alunoId;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal valor;

  @Column(name = "mes_referencia", nullable = false, length = 7)
  private String mesReferencia;

  @Column(name = "data_vencimento", nullable = false)
  private LocalDate dataVencimento;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private StatusPagamento status;

  @Column(name = "data_pagamento")
  private LocalDate dataPagamento;

  @Column(name = "data_criacao", nullable = false, updatable = false)
  private LocalDateTime dataCriacao;

  @PrePersist
  protected void onCreate() {
    dataCriacao = LocalDateTime.now();
    if (status == null) {
      status = StatusPagamento.PENDENTE;
    }
  }
}
