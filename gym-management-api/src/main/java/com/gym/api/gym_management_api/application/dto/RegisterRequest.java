package com.gym.api.gym_management_api.application.dto;

import com.gym.api.gym_management_api.domain.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
  
  @NotBlank(message = "Nome é obrigatório")
  @Size(min = 3, max = 50, message = "Nome deve conter entre 3 e 50 caracteres")
  private String nome;

  @NotBlank(message = "Email é obrigatório")
  @Email(message = "Email inválido")
  private String email;

  @NotBlank(message = "Senha é obrigatória")
  @Size(min = 6, message = "Senha deve conter no mínimo 6 caracteres")
  private String senha;

  private String telefone;

  @NotNull(message = "Idade é obrigatória")
  private Role role;

}
