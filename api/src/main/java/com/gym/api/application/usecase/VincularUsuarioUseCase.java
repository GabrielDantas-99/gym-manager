package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.UsuarioResponse;
import com.gym.api.application.dto.VincularUsuarioRequest;
import com.gym.api.application.dto.VinculoResponse;
import com.gym.api.domain.exception.BusinessException;
import com.gym.api.domain.exception.ResourceNotFoundException;
import com.gym.api.domain.model.Role;
import com.gym.api.domain.model.Vinculo;
import com.gym.api.domain.repository.AcademiaRepository;
import com.gym.api.domain.repository.UsuarioRepository;
import com.gym.api.domain.repository.VinculoRepository;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VincularUsuarioUseCase {

  private final VinculoRepository vinculoRepository;
  private final UsuarioRepository usuarioRepository;
  private final AcademiaRepository academiaRepository;
  private final AuthenticatedUserService authenticatedUserService;

  @Transactional
  public VinculoResponse execute(VincularUsuarioRequest request) {
    var usuarioAutenticado = authenticatedUserService.getAuthenticatedUser();

    if (!usuarioAutenticado.isAdmin()) {
      throw new BusinessException("Apenas administradores podem vincular usuários");
    }

    var usuario = usuarioRepository.findById(request.getUsuarioId())
        .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

    var academia = academiaRepository.findById(request.getAcademiaId())
        .orElseThrow(() -> new ResourceNotFoundException("Academia não encontrada"));

    if (vinculoRepository.existsByUsuarioIdAndAcademiaId(request.getUsuarioId(), request.getAcademiaId())) {
      throw new BusinessException("Usuário já vinculado a esta academia");
    }

    Long personalId = null;
    UsuarioResponse personalResponse = null;

    if (usuario.getRole() == Role.ALUNO && request.getPersonalId() != null) {
      var personal = usuarioRepository.findById(request.getPersonalId())
          .orElseThrow(() -> new ResourceNotFoundException("Personal não encontrado"));

      if (personal.getRole() != Role.PERSONAL) {
        throw new BusinessException("O ID informado não pertence a um personal");
      }

      personalId = personal.getId();
      personalResponse = UsuarioResponse.builder()
          .id(personal.getId())
          .nome(personal.getNome())
          .email(personal.getEmail())
          .telefone(personal.getTelefone())
          .role(personal.getRole())
          .ativo(personal.getAtivo())
          .dataCadastro(personal.getDataCadastro())
          .build();
    }

    var vinculo = Vinculo.builder()
        .usuarioId(request.getUsuarioId())
        .academiaId(request.getAcademiaId())
        .personalId(personalId)
        .ativo(true)
        .dataVinculo(LocalDateTime.now())
        .dataAtualizacao(LocalDateTime.now())
        .build();

    vinculo = vinculoRepository.save(vinculo);

    return VinculoResponse.builder()
        .id(vinculo.getId())
        .usuario(UsuarioResponse.builder()
            .id(usuario.getId())
            .nome(usuario.getNome())
            .email(usuario.getEmail())
            .telefone(usuario.getTelefone())
            .role(usuario.getRole())
            .ativo(usuario.getAtivo())
            .dataCadastro(usuario.getDataCadastro())
            .build())
        .academiaId(vinculo.getAcademiaId())
        .personal(personalResponse)
        .ativo(vinculo.getAtivo())
        .dataVinculo(vinculo.getDataVinculo())
        .build();
  }
}
