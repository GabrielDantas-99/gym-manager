package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.MensalidadeResponse;
import com.gym.api.domain.exception.BusinessException;
import com.gym.api.domain.exception.ResourceNotFoundException;
import com.gym.api.domain.repository.MensalidadeRepository;
import com.gym.api.domain.repository.UsuarioRepository;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

@Service
@RequiredArgsConstructor
public class RegistrarPagamentoUseCase {

  private final MensalidadeRepository mensalidadeRepository;
  private final UsuarioRepository usuarioRepository;
  private final AuthenticatedUserService authenticatedUserService;

  @Transactional
  public MensalidadeResponse execute(Long mensalidadeId) {
    var usuario = authenticatedUserService.getAuthenticatedUser();

    if (!usuario.isAdmin()) {
      throw new BusinessException("Apenas administradores podem registrar pagamentos");
    }

    var mensalidade = mensalidadeRepository.findById(mensalidadeId)
        .orElseThrow(() -> new ResourceNotFoundException("Mensalidade não encontrada"));

    if (mensalidade.isPago()) {
      throw new BusinessException("Mensalidade já está paga");
    }

    mensalidade.marcarComoPago();
    mensalidade = mensalidadeRepository.save(mensalidade);

    var aluno = usuarioRepository.findById(mensalidade.getAlunoId()).orElse(null);

    return MensalidadeResponse.builder()
        .id(mensalidade.getId())
        .alunoId(mensalidade.getAlunoId())
        .nomeAluno(aluno != null ? aluno.getNome() : null)
        .valor(mensalidade.getValor())
        .mesReferencia(mensalidade.getMesReferencia())
        .dataVencimento(mensalidade.getDataVencimento())
        .status(mensalidade.getStatus())
        .dataPagamento(mensalidade.getDataPagamento())
        .build();
  }
}
