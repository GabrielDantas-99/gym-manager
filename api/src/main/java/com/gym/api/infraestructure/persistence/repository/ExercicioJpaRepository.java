package com.gym.api.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.api.infraestructure.persistence.entity.ExercicioEntity;

import java.util.List;

@Repository
public interface ExercicioJpaRepository extends JpaRepository<ExercicioEntity, Long> {

  List<ExercicioEntity> findByFichaTreinoIdOrderByOrdemAsc(Long fichaTreinoId);

  void deleteByFichaTreinoId(Long fichaTreinoId);
}
