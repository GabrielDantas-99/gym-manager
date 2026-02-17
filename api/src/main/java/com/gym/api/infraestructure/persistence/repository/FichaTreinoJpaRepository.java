package com.gym.api.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.api.infraestructure.persistence.entity.FichaTreinoEntity;

import java.util.List;

@Repository
public interface FichaTreinoJpaRepository extends JpaRepository<FichaTreinoEntity, Long> {

  List<FichaTreinoEntity> findByAlunoId(Long alunoId);

  List<FichaTreinoEntity> findByPersonalId(Long personalId);
}
