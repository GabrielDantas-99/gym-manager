package com.gym.api.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioRequest {

  @NotBlank(message = "Nome do exercício é obrigatório")
  private String nome;

  @NotNull(message = "Número de séries é obrigatório")
  @Positive(message = "Número de séries deve ser positivo")
  private Integer series;

  @NotBlank(message = "Repetições são obrigatórias")
  private String repeticoes;

  private String observacoes;

  @NotNull(message = "Ordem é obrigatória")
  @Positive(message = "Ordem deve ser positiva")
  private Integer ordem;
}
