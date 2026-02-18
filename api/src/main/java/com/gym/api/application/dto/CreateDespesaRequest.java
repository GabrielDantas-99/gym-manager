package com.gym.api.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.gym.api.domain.model.TipoDespesa;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDespesaRequest {

  @NotNull(message = "ID da academia é obrigatório")
  private Long academiaId;

  @NotBlank(message = "Descrição é obrigatória")
  private String descricao;

  @NotNull(message = "Valor é obrigatório")
  @Positive(message = "Valor deve ser positivo")
  private BigDecimal valor;

  @NotNull(message = "Tipo é obrigatório")
  private TipoDespesa tipo;

  @NotNull(message = "Data da despesa é obrigatória")
  private LocalDate dataDespesa;
}
