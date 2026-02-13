package com.gym.api.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VincularUsuarioRequest {

  @NotNull(message = "ID do usuário é obrigatório")
  private Long usuarioId;

  @NotNull(message = "ID da academia é obrigatório")
  private Long academiaId;

  private Long personalId;
}
