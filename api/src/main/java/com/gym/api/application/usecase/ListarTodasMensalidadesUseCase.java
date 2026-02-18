package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.MensalidadeResponse;
import com.gym.api.domain.exception.BusinessException;
import com.gym.api.domain.repository.MensalidadeRepository;
import com.gym.api.domain.repository.UsuarioRepository;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarTodasMensalidadesUseCase {

  private final MensalidadeRepository mensalidadeRepository;
  private final UsuarioRepository usuarioRepository;
  private final AuthenticatedUserService authenticatedUserService;

  @Transactional(readOnly = true)
  public List<MensalidadeResponse> execute() {
    var usuario = authenticatedUserService.getAuthenticatedUser();

    if (!usuario.isAdmin()) {
      throw new BusinessException("Apenas administradores podem listar todas as mensalidades");
    }

    var mensalidades = mensalidadeRepository.findAll();

    return mensalidades.stream()
        .map(mensalidade -> {
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
        })
        .collect(Collectors.toList());
  }
}
