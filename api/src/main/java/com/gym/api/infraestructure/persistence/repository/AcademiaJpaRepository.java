package com.gym.api.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.api.infraestructure.persistence.entity.AcademiaEntity;

import java.util.List;

@Repository
public interface AcademiaJpaRepository extends JpaRepository<AcademiaEntity, Long> {

  List<AcademiaEntity> findByAdminId(Long adminId);

}
