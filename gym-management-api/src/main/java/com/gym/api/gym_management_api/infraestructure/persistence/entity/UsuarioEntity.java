package com.gym.api.gym_management_api.infraestructure.persistence.entity;

import java.time.LocalDateTime;

import com.gym.api.gym_management_api.domain.model.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String nome;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @Column(nullable = false)
  private String senha;

  @Column(length = 20)
  private String telefone;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(nullable = false)
  private Boolean ativo;

  @Column(name = "data_cadastro", nullable = false, updatable = false)
  private LocalDateTime dataCadastro;

  @Column(name = "data_atualizacao")
  private LocalDateTime dataAtualizacao;

  @PrePersist
  protected void onCreate() {
    this.dataCadastro = LocalDateTime.now();
    this.dataAtualizacao = LocalDateTime.now();
    if (ativo == null) {
      this.ativo = true; 
    }
  }

  @PreUpdate
  protected void onUpdate() {
    this.dataAtualizacao = LocalDateTime.now();
  }

}
