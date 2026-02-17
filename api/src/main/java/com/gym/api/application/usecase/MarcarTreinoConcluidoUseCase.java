package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.domain.exception.BusinessException;
import com.gym.api.domain.exception.ResourceNotFoundException;
import com.gym.api.domain.model.HistoricoTreino;
import com.gym.api.domain.repository.FichaTreinoRepository;
import com.gym.api.domain.repository.HistoricoTreinoRepository;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MarcarTreinoConcluidoUseCase {

  private final FichaTreinoRepository fichaTreinoRepository;
  private final HistoricoTreinoRepository historicoTreinoRepository;
  private final AuthenticatedUserService authenticatedUserService;

  @Transactional
  public void execute(Long fichaTreinoId) {
    var aluno = authenticatedUserService.getAuthenticatedUser();

    if (!aluno.isAluno()) {
      throw new BusinessException("Apenas alunos podem marcar treinos como concluídos");
    }

    var fichaTreino = fichaTreinoRepository.findById(fichaTreinoId)
        .orElseThrow(() -> new ResourceNotFoundException("Ficha de treino não encontrada"));

    if (!fichaTreino.getAlunoId().equals(aluno.getId())) {
      throw new BusinessException("Você só pode marcar seus próprios treinos como concluídos");
    }

    var historico = HistoricoTreino.builder()
        .fichaTreinoId(fichaTreinoId)
        .alunoId(aluno.getId())
        .dataConclusao(LocalDateTime.now())
        .build();

    historicoTreinoRepository.save(historico);
  }
}
