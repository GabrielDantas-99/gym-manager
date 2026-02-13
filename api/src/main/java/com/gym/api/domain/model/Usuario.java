package com.gym.api.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

  private Long id;
  private String nome;
  private String email;
  private String senha;
  private String telefone;
  private Role role;
  private Boolean ativo;
  private LocalDateTime dataCadastro;
  private LocalDateTime dataAtualizacao;

  public void ativar() {
    this.ativo = true;
  }

  public void inativar() {
    this.ativo = false;
  }

  public boolean isAdmin() {
    return Role.ADMIN.equals(this.role);
  }

  public boolean isPersonal() {
    return Role.PERSONAL.equals(this.role);
  }

  public boolean isAluno() {
    return Role.ALUNO.equals(this.role);
  }

}
