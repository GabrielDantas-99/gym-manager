package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.DashboardResponse;
import com.gym.api.domain.exception.BusinessException;
import com.gym.api.domain.model.Despesa;
import com.gym.api.domain.model.Role;
import com.gym.api.domain.model.StatusPagamento;
import com.gym.api.domain.repository.DespesaRepository;
import com.gym.api.domain.repository.MensalidadeRepository;
import com.gym.api.domain.repository.UsuarioRepository;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class GetDashboardUseCase {

  private final UsuarioRepository usuarioRepository;
  private final MensalidadeRepository mensalidadeRepository;
  private final DespesaRepository despesaRepository;
  private final AuthenticatedUserService authenticatedUserService;

  @Transactional(readOnly = true)
  public DashboardResponse execute() {
    var usuario = authenticatedUserService.getAuthenticatedUser();

    if (!usuario.isAdmin()) {
      throw new BusinessException("Apenas administradores podem acessar o dashboard");
    }

    long totalAlunos = usuarioRepository.findAll().stream()
        .filter(u -> u.getRole() == Role.ALUNO && u.getAtivo())
        .count();

    long totalPersonais = usuarioRepository.findAll().stream()
        .filter(u -> u.getRole() == Role.PERSONAL && u.getAtivo())
        .count();

    var mensalidadesPagas = mensalidadeRepository.findByStatus(StatusPagamento.PAGO);
    var mensalidadesPendentes = mensalidadeRepository.findByStatus(StatusPagamento.PENDENTE);

    BigDecimal receitaMensal = mensalidadesPagas.stream()
        .map(m -> m.getValor())
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal totalDespesas = despesaRepository.findAll().stream()
        .map(Despesa::getValor)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal receitaLiquida = receitaMensal.subtract(totalDespesas);

    return DashboardResponse.builder()
        .totalAlunos(totalAlunos)
        .totalPersonais(totalPersonais)
        .receitaMensal(receitaMensal)
        .totalDespesas(totalDespesas)
        .receitaLiquida(receitaLiquida)
        .mensalidadesPendentes((long) mensalidadesPendentes.size())
        .mensalidadesPagas((long) mensalidadesPagas.size())
        .build();
  }
}
