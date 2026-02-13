package com.gym.api.gym_management_api.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gym.api.gym_management_api.domain.model.Academia;

public interface AcademiaRepository {
    
  Academia save(Academia academia);
  
  Optional<Academia> findById(Long id);
  
  List<Academia> findAll();
  
  List<Academia> findByAdminId(Long adminId);
  
  void deleteById(Long id);
}
