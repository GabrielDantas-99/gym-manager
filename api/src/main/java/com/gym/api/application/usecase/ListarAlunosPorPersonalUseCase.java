package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.UsuarioResponse;
import com.gym.api.domain.exception.BusinessException;
import com.gym.api.domain.repository.UsuarioRepository;
import com.gym.api.domain.repository.VinculoRepository;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarAlunosPorPersonalUseCase {

  private final VinculoRepository vinculoRepository;
  private final UsuarioRepository usuarioRepository;
  private final AuthenticatedUserService authenticatedUserService;

  @Transactional(readOnly = true)
  public List<UsuarioResponse> execute() {
    var personal = authenticatedUserService.getAuthenticatedUser();

    if (!personal.isPersonal()) {
      throw new BusinessException("Apenas personais podem listar seus alunos");
    }

    var vinculos = vinculoRepository.findByPersonalId(personal.getId());

    return vinculos.stream()
        .map(vinculo -> usuarioRepository.findById(vinculo.getUsuarioId()))
        .filter(opt -> opt.isPresent())
        .map(opt -> {
          var aluno = opt.get();
          return UsuarioResponse.builder()
              .id(aluno.getId())
              .nome(aluno.getNome())
              .email(aluno.getEmail())
              .telefone(aluno.getTelefone())
              .role(aluno.getRole())
              .ativo(aluno.getAtivo())
              .dataCadastro(aluno.getDataCadastro())
              .build();
        })
        .collect(Collectors.toList());
  }
}
