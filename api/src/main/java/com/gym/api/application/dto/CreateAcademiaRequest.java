package com.gym.api.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAcademiaRequest {

  @NotBlank(message = "Nome é obrigatório")
  @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
  private String nome;

  @Size(max = 200, message = "Endereço deve ter no máximo 200 caracteres")
  private String endereco;

  @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
  private String telefone;
}
