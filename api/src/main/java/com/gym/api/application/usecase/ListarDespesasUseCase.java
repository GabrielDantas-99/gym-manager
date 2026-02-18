package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.DespesaResponse;
import com.gym.api.domain.exception.BusinessException;
import com.gym.api.domain.repository.AcademiaRepository;
import com.gym.api.domain.repository.DespesaRepository;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarDespesasUseCase {

  private final DespesaRepository despesaRepository;
  private final AcademiaRepository academiaRepository;
  private final AuthenticatedUserService authenticatedUserService;

  @Transactional(readOnly = true)
  public List<DespesaResponse> execute() {
    var usuario = authenticatedUserService.getAuthenticatedUser();

    if (!usuario.isAdmin()) {
      throw new BusinessException("Apenas administradores podem listar despesas");
    }

    var despesas = despesaRepository.findAll();

    return despesas.stream()
        .map(despesa -> {
          var academia = academiaRepository.findById(despesa.getAcademiaId()).orElse(null);

          return DespesaResponse.builder()
              .id(despesa.getId())
              .academiaId(despesa.getAcademiaId())
              .nomeAcademia(academia != null ? academia.getNome() : null)
              .descricao(despesa.getDescricao())
              .valor(despesa.getValor())
              .tipo(despesa.getTipo())
              .dataDespesa(despesa.getDataDespesa())
              .build();
        })
        .collect(Collectors.toList());
  }
}
