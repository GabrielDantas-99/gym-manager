package com.gym.api.gym_management_api.application.usecase;

import com.gym.api.gym_management_api.application.dto.AuthResponse;
import com.gym.api.gym_management_api.application.dto.LoginRequest;
import com.gym.api.gym_management_api.application.dto.RegisterRequest;
import com.gym.api.gym_management_api.domain.exception.BusinessException;
import com.gym.api.gym_management_api.domain.model.Usuario;
import com.gym.api.gym_management_api.domain.repository.UsuarioRepository;
import com.gym.api.gym_management_api.infraestructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthUseCase {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }
        
        var usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .telefone(request.getTelefone())
                .role(request.getRole())
                .ativo(true)
                .dataCadastro(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();
        
        usuario = usuarioRepository.save(usuario);
        
        var userDetails = User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .authorities(Collections.emptyList())
                .build();
        
        var token = jwtService.generateToken(userDetails);
        
        return AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .userId(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .role(usuario.getRole())
                .build();
    }
    
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSenha()
                )
        );
        
        var usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));
        
        if (!usuario.getAtivo()) {
            throw new BusinessException("Usuário inativo");
        }
        
        var userDetails = User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .authorities(Collections.emptyList())
                .build();
        
        var token = jwtService.generateToken(userDetails);
        
        return AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .userId(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .role(usuario.getRole())
                .build();
    }
}
