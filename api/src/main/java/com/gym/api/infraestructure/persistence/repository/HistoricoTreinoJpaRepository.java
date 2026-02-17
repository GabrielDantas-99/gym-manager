package com.gym.api.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.api.infraestructure.persistence.entity.HistoricoTreinoEntity;

import java.util.List;

@Repository
public interface HistoricoTreinoJpaRepository extends JpaRepository<HistoricoTreinoEntity, Long> {

  List<HistoricoTreinoEntity> findByAlunoIdOrderByDataConclusaoDesc(Long alunoId);

  List<HistoricoTreinoEntity> findByFichaTreinoIdOrderByDataConclusaoDesc(Long fichaTreinoId);
}
