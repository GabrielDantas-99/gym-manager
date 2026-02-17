package com.gym.api.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exercicios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "ficha_treino_id", nullable = false)
  private Long fichaTreinoId;

  @Column(nullable = false, length = 100)
  private String nome;

  @Column(nullable = false)
  private Integer series;

  @Column(nullable = false, length = 50)
  private String repeticoes;

  @Column(columnDefinition = "TEXT")
  private String observacoes;

  @Column(nullable = false)
  private Integer ordem;
}
