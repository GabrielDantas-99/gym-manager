package com.gym.api.gym_management_api.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gym.api.gym_management_api.domain.model.Vinculo;

public interface VinculoRepository {

  Vinculo save(Vinculo vinculo);

  Optional<Vinculo> findById(Long id);

  Optional<Vinculo> findByUsuarioIdAndAcademiaId(Long usuarioId, Long academiaId);

  List<Vinculo> findByAcademiaId(Long academiaId);

  List<Vinculo> findByUsuarioId(Long usuarioId);

  List<Vinculo> findByPersonalId(Long personalId);

  void deleteById(Long id);

  boolean existsByUsuarioIdAndAcademiaId(Long usuarioId, Long academiaId);
}
