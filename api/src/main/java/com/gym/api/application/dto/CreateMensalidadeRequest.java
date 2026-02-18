package com.gym.api.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMensalidadeRequest {

  @NotNull(message = "ID do aluno é obrigatório")
  private Long alunoId;

  @NotNull(message = "Valor é obrigatório")
  @Positive(message = "Valor deve ser positivo")
  private BigDecimal valor;

  @NotBlank(message = "Mês de referência é obrigatório")
  private String mesReferencia;

  @NotNull(message = "Data de vencimento é obrigatória")
  private LocalDate dataVencimento;
}
