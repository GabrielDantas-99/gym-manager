package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.AcademiaResponse;
import com.gym.api.application.dto.CreateAcademiaRequest;
import com.gym.api.domain.exception.BusinessException;
import com.gym.api.domain.model.Academia;
import com.gym.api.domain.repository.AcademiaRepository;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateAcademiaUseCase {

  private final AcademiaRepository academiaRepository;
  private final AuthenticatedUserService authenticatedUserService;

  @Transactional
  public AcademiaResponse execute(CreateAcademiaRequest request) {
    var usuario = authenticatedUserService.getAuthenticatedUser();

    if (!usuario.isAdmin()) {
      throw new BusinessException("Apenas administradores podem cadastrar academias");
    }

    var academia = Academia.builder()
        .nome(request.getNome())
        .endereco(request.getEndereco())
        .telefone(request.getTelefone())
        .adminId(usuario.getId())
        .ativa(true)
        .dataCadastro(LocalDateTime.now())
        .dataAtualizacao(LocalDateTime.now())
        .build();

    academia = academiaRepository.save(academia);

    return AcademiaResponse.builder()
        .id(academia.getId())
        .nome(academia.getNome())
        .endereco(academia.getEndereco())
        .telefone(academia.getTelefone())
        .adminId(academia.getAdminId())
        .ativa(academia.getAtiva())
        .dataCadastro(academia.getDataCadastro())
        .build();
  }
}
