package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.AcademiaResponse;
import com.gym.api.domain.repository.AcademiaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListAcademiasUseCase {

  private final AcademiaRepository academiaRepository;

  @Transactional(readOnly = true)
  public List<AcademiaResponse> execute() {
    return academiaRepository.findAll()
        .stream()
        .map(academia -> AcademiaResponse.builder()
            .id(academia.getId())
            .nome(academia.getNome())
            .endereco(academia.getEndereco())
            .telefone(academia.getTelefone())
            .adminId(academia.getAdminId())
            .ativa(academia.getAtiva())
            .dataCadastro(academia.getDataCadastro())
            .build())
        .collect(Collectors.toList());
  }
}
