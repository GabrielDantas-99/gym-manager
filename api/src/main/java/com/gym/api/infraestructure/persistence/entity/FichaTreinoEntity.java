package com.gym.api.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.gym.api.domain.model.DiaSemana;

@Entity
@Table(name = "fichas_treino")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FichaTreinoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "aluno_id", nullable = false)
  private Long alunoId;

  @Column(name = "personal_id", nullable = false)
  private Long personalId;

  @Enumerated(EnumType.STRING)
  @Column(name = "dia_semana", nullable = false, length = 20)
  private DiaSemana diaSemana;

  @Column(name = "grupo_muscular", length = 100)
  private String grupoMuscular;

  @Column(nullable = false)
  private Boolean ativa;

  @Column(name = "data_criacao", nullable = false, updatable = false)
  private LocalDateTime dataCriacao;

  @Column(name = "data_atualizacao")
  private LocalDateTime dataAtualizacao;

  @PrePersist
  protected void onCreate() {
    dataCriacao = LocalDateTime.now();
    dataAtualizacao = LocalDateTime.now();
    if (ativa == null) {
      ativa = true;
    }
  }

  @PreUpdate
  protected void onUpdate() {
    dataAtualizacao = LocalDateTime.now();
  }
}
