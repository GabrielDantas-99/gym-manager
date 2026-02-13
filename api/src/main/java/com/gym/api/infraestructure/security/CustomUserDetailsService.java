package com.gym.api.infraestructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gym.api.domain.repository.UsuarioRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

        private final UsuarioRepository usuarioRepository;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                var usuario = usuarioRepository.findByEmail(email)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

                return User.builder()
                                .username(usuario.getEmail())
                                .password(usuario.getSenha())
                                .authorities(Collections.singletonList(
                                                new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name())))
                                .accountExpired(false)
                                .accountLocked(!usuario.getAtivo())
                                .credentialsExpired(false)
                                .disabled(!usuario.getAtivo())
                                .build();
        }
}