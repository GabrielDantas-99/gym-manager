package com.gym.api.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "academias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademiaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String nome;

  @Column(length = 200)
  private String endereco;

  @Column(length = 20)
  private String telefone;

  @Column(name = "admin_id", nullable = false)
  private Long adminId;

  @Column(nullable = false)
  private Boolean ativa;

  @Column(name = "data_cadastro", nullable = false, updatable = false)
  private LocalDateTime dataCadastro;

  @Column(name = "data_atualizacao")
  private LocalDateTime dataAtualizacao;

  @PrePersist
  protected void onCreate() {
    dataCadastro = LocalDateTime.now();
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
