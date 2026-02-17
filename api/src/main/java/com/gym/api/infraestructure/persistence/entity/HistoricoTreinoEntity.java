package com.gym.api.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "historico_treino")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoTreinoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "ficha_treino_id", nullable = false)
  private Long fichaTreinoId;

  @Column(name = "aluno_id", nullable = false)
  private Long alunoId;

  @Column(name = "data_conclusao", nullable = false)
  private LocalDateTime dataConclusao;

  @PrePersist
  protected void onCreate() {
    if (dataConclusao == null) {
      dataConclusao = LocalDateTime.now();
    }
  }
}
