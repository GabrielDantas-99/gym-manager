package com.gym.api.gym_management_api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.gym_management_api.application.dto.UsuarioResponse;
import com.gym.api.gym_management_api.application.dto.VinculoResponse;
import com.gym.api.gym_management_api.domain.exception.ResourceNotFoundException;
import com.gym.api.gym_management_api.domain.repository.AcademiaRepository;
import com.gym.api.gym_management_api.domain.repository.UsuarioRepository;
import com.gym.api.gym_management_api.domain.repository.VinculoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarUsuariosPorAcademiaUseCase {

  private final VinculoRepository vinculoRepository;
  private final UsuarioRepository usuarioRepository;
  private final AcademiaRepository academiaRepository;

  @Transactional(readOnly = true)
  public List<VinculoResponse> execute(Long academiaId) {
    academiaRepository.findById(academiaId)
        .orElseThrow(() -> new ResourceNotFoundException("Academia não encontrada"));

    var vinculos = vinculoRepository.findByAcademiaId(academiaId);

    return vinculos.stream()
        .map(vinculo -> {
          var usuario = usuarioRepository.findById(vinculo.getUsuarioId()).orElse(null);

          UsuarioResponse usuarioResponse = null;
          if (usuario != null) {
            usuarioResponse = UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .telefone(usuario.getTelefone())
                .role(usuario.getRole())
                .ativo(usuario.getAtivo())
                .dataCadastro(usuario.getDataCadastro())
                .build();
          }

          UsuarioResponse personalResponse = null;
          if (vinculo.getPersonalId() != null) {
            var personal = usuarioRepository.findById(vinculo.getPersonalId()).orElse(null);
            if (personal != null) {
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
          }

          return VinculoResponse.builder()
              .id(vinculo.getId())
              .usuario(usuarioResponse)
              .academiaId(vinculo.getAcademiaId())
              .personal(personalResponse)
              .ativo(vinculo.getAtivo())
              .dataVinculo(vinculo.getDataVinculo())
              .build();
        })
        .collect(Collectors.toList());
  }
}
