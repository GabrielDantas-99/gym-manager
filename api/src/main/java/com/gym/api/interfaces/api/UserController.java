package com.gym.api.interfaces.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.api.application.dto.UsuarioResponse;
import com.gym.api.application.usecase.GetUserProfileUseCase;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final GetUserProfileUseCase getUserProfileUseCase;

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponse> getProfile() {
        UsuarioResponse response = getUserProfileUseCase.execute();
        return ResponseEntity.ok(response);
    }
}
