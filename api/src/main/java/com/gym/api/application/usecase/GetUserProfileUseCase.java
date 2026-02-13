package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.UsuarioResponse;
import com.gym.api.domain.model.Usuario;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

@Service
@RequiredArgsConstructor
public class GetUserProfileUseCase {

    private final AuthenticatedUserService authenticatedUserService;

    @Transactional(readOnly = true)
    public UsuarioResponse execute() {
        Usuario usuario = authenticatedUserService.getAuthenticatedUser();

        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .telefone(usuario.getTelefone())
                .role(usuario.getRole())
                .ativo(usuario.getAtivo())
                .dataCadastro(usuario.getDataCadastro())
                .build();
    }
}
