package com.gym.api.gym_management_api.application.dto;

import com.gym.api.gym_management_api.domain.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
  private String token;
  private String type;
  private Long userId;
  private String nome;
  private String email;
  private Role role;
}
