package com.gym.api.gym_management_api.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "vinculos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VinculoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "usuario_id", nullable = false)
  private Long usuarioId;

  @Column(name = "academia_id", nullable = false)
  private Long academiaId;

  @Column(name = "personal_id")
  private Long personalId;

  @Column(nullable = false)
  private Boolean ativo;

  @Column(name = "data_vinculo", nullable = false, updatable = false)
  private LocalDateTime dataVinculo;

  @Column(name = "data_atualizacao")
  private LocalDateTime dataAtualizacao;

  @PrePersist
  protected void onCreate() {
    dataVinculo = LocalDateTime.now();
    dataAtualizacao = LocalDateTime.now();
    if (ativo == null) {
      ativo = true;
    }
  }

  @PreUpdate
  protected void onUpdate() {
    dataAtualizacao = LocalDateTime.now();
  }
}
