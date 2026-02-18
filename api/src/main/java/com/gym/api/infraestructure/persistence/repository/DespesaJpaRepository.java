package com.gym.api.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.api.domain.model.TipoDespesa;
import com.gym.api.infraestructure.persistence.entity.DespesaEntity;

import java.util.List;

@Repository
public interface DespesaJpaRepository extends JpaRepository<DespesaEntity, Long> {

  List<DespesaEntity> findByAcademiaIdOrderByDataDespesaDesc(Long academiaId);

  List<DespesaEntity> findByTipo(TipoDespesa tipo);
}
