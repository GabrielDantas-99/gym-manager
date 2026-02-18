package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.CreateMensalidadeRequest;
import com.gym.api.application.dto.MensalidadeResponse;
import com.gym.api.domain.exception.BusinessException;
import com.gym.api.domain.exception.ResourceNotFoundException;
import com.gym.api.domain.model.Mensalidade;
import com.gym.api.domain.model.StatusPagamento;
import com.gym.api.domain.repository.MensalidadeRepository;
import com.gym.api.domain.repository.UsuarioRepository;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateMensalidadeUseCase {

  private final MensalidadeRepository mensalidadeRepository;
  private final UsuarioRepository usuarioRepository;
  private final AuthenticatedUserService authenticatedUserService;

  @Transactional
  public MensalidadeResponse execute(CreateMensalidadeRequest request) {
    var usuario = authenticatedUserService.getAuthenticatedUser();

    if (!usuario.isAdmin()) {
      throw new BusinessException("Apenas administradores podem criar mensalidades");
    }

    var aluno = usuarioRepository.findById(request.getAlunoId())
        .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

    if (!aluno.isAluno()) {
      throw new BusinessException("O usuário informado não é um aluno");
    }

    var mensalidade = Mensalidade.builder()
        .alunoId(request.getAlunoId())
        .valor(request.getValor())
        .mesReferencia(request.getMesReferencia())
        .dataVencimento(request.getDataVencimento())
        .status(StatusPagamento.PENDENTE)
        .dataCriacao(LocalDateTime.now())
        .build();

    mensalidade = mensalidadeRepository.save(mensalidade);

    return MensalidadeResponse.builder()
        .id(mensalidade.getId())
        .alunoId(mensalidade.getAlunoId())
        .nomeAluno(aluno.getNome())
        .valor(mensalidade.getValor())
        .mesReferencia(mensalidade.getMesReferencia())
        .dataVencimento(mensalidade.getDataVencimento())
        .status(mensalidade.getStatus())
        .dataPagamento(mensalidade.getDataPagamento())
        .build();
  }
}
