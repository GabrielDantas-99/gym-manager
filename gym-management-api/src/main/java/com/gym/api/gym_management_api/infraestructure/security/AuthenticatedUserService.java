package com.gym.api.gym_management_api.infraestructure.security;

import com.gym.api.gym_management_api.domain.exception.BusinessException;
import com.gym.api.gym_management_api.domain.model.Usuario;
import com.gym.api.gym_management_api.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService {
    
    private final UsuarioRepository usuarioRepository;
    
    public Usuario getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessException("Usuário não autenticado");
        }
        
        Object principal = authentication.getPrincipal();
        
        if (!(principal instanceof UserDetails)) {
            throw new BusinessException("Usuário não autenticado");
        }
        
        String email = ((UserDetails) principal).getUsername();
        
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));
    }
    
    public Long getAuthenticatedUserId() {
        return getAuthenticatedUser().getId();
    }
    
    public String getAuthenticatedUserEmail() {
        return getAuthenticatedUser().getEmail();
    }
    
    public boolean isAdmin() {
        return getAuthenticatedUser().isAdmin();
    }
    
    public boolean isPersonal() {
        return getAuthenticatedUser().isPersonal();
    }
    
    public boolean isAluno() {
        return getAuthenticatedUser().isAluno();
    }
}
