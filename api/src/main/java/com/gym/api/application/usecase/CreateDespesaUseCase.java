package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.CreateDespesaRequest;
import com.gym.api.application.dto.DespesaResponse;
import com.gym.api.domain.exception.BusinessException;
import com.gym.api.domain.exception.ResourceNotFoundException;
import com.gym.api.domain.model.Despesa;
import com.gym.api.domain.repository.AcademiaRepository;
import com.gym.api.domain.repository.DespesaRepository;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateDespesaUseCase {

  private final DespesaRepository despesaRepository;
  private final AcademiaRepository academiaRepository;
  private final AuthenticatedUserService authenticatedUserService;

  @Transactional
  public DespesaResponse execute(CreateDespesaRequest request) {
    var usuario = authenticatedUserService.getAuthenticatedUser();

    if (!usuario.isAdmin()) {
      throw new BusinessException("Apenas administradores podem criar despesas");
    }

    var academia = academiaRepository.findById(request.getAcademiaId())
        .orElseThrow(() -> new ResourceNotFoundException("Academia não encontrada"));

    var despesa = Despesa.builder()
        .academiaId(request.getAcademiaId())
        .descricao(request.getDescricao())
        .valor(request.getValor())
        .tipo(request.getTipo())
        .dataDespesa(request.getDataDespesa())
        .dataCriacao(LocalDateTime.now())
        .build();

    despesa = despesaRepository.save(despesa);

    return DespesaResponse.builder()
        .id(despesa.getId())
        .academiaId(despesa.getAcademiaId())
        .nomeAcademia(academia.getNome())
        .descricao(despesa.getDescricao())
        .valor(despesa.getValor())
        .tipo(despesa.getTipo())
        .dataDespesa(despesa.getDataDespesa())
        .build();
  }
}
