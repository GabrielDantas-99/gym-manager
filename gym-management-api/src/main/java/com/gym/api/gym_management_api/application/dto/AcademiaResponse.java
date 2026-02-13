package com.gym.api.gym_management_api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademiaResponse {

  private Long id;
  private String nome;
  private String endereco;
  private String telefone;
  private Long adminId;
  private Boolean ativa;
  private LocalDateTime dataCadastro;
}
