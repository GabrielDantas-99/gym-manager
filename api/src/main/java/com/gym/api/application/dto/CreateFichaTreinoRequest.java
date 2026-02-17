package com.gym.api.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.gym.api.domain.model.DiaSemana;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFichaTreinoRequest {

  @NotNull(message = "ID do aluno é obrigatório")
  private Long alunoId;

  @NotNull(message = "Dia da semana é obrigatório")
  private DiaSemana diaSemana;

  private String grupoMuscular;

  @NotEmpty(message = "Deve haver pelo menos um exercício")
  @Valid
  private List<ExercicioRequest> exercicios;
}
