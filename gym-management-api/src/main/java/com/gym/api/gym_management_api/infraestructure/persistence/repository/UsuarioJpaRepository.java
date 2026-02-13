package com.gym.api.gym_management_api.infraestructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.api.gym_management_api.infraestructure.persistence.entity.UsuarioEntity;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

  Optional<UsuarioEntity> findByEmail(String email);
  
  boolean existsByEmail(String email);
  
}
