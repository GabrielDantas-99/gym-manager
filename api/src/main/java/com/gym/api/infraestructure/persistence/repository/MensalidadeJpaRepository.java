package com.gym.api.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.api.domain.model.StatusPagamento;
import com.gym.api.infraestructure.persistence.entity.MensalidadeEntity;

import java.util.List;

@Repository
public interface MensalidadeJpaRepository extends JpaRepository<MensalidadeEntity, Long> {

  List<MensalidadeEntity> findByAlunoIdOrderByDataVencimentoDesc(Long alunoId);

  List<MensalidadeEntity> findByStatus(StatusPagamento status);
}
