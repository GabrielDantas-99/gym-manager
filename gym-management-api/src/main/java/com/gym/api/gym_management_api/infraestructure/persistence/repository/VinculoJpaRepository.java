package com.gym.api.gym_management_api.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.api.gym_management_api.infraestructure.persistence.entity.VinculoEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface VinculoJpaRepository extends JpaRepository<VinculoEntity, Long> {

  Optional<VinculoEntity> findByUsuarioIdAndAcademiaId(Long usuarioId, Long academiaId);

  List<VinculoEntity> findByAcademiaId(Long academiaId);

  List<VinculoEntity> findByUsuarioId(Long usuarioId);

  List<VinculoEntity> findByPersonalId(Long personalId);

  boolean existsByUsuarioIdAndAcademiaId(Long usuarioId, Long academiaId);
}
