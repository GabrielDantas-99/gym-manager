package com.gym.api.gym_management_api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Academia {
    
  private Long id;
  private String nome;
  private String endereco;
  private String telefone;
  private Long adminId;
  private Boolean ativa;
  private LocalDateTime dataCadastro;
  private LocalDateTime dataAtualizacao;
  
  public void ativar() {
    this.ativa = true;
  }
  
  public void inativar() {
    this.ativa = false;
  }
  
}
