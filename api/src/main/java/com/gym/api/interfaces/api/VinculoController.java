package com.gym.api.interfaces.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.api.application.dto.UsuarioResponse;
import com.gym.api.application.dto.VincularUsuarioRequest;
import com.gym.api.application.dto.VinculoResponse;
import com.gym.api.application.usecase.ListarAlunosPorPersonalUseCase;
import com.gym.api.application.usecase.ListarUsuariosPorAcademiaUseCase;
import com.gym.api.application.usecase.VincularUsuarioUseCase;
import com.gym.api.infraestructure.security.annotation.IsAdmin;
import com.gym.api.infraestructure.security.annotation.IsPersonal;

import java.util.List;

@RestController
@RequestMapping("/api/vinculos")
@RequiredArgsConstructor
public class VinculoController {

  private final VincularUsuarioUseCase vincularUsuarioUseCase;
  private final ListarUsuariosPorAcademiaUseCase listarUsuariosPorAcademiaUseCase;
  private final ListarAlunosPorPersonalUseCase listarAlunosPorPersonalUseCase;

  @PostMapping
  @IsAdmin
  public ResponseEntity<VinculoResponse> vincular(@Valid @RequestBody VincularUsuarioRequest request) {
    VinculoResponse response = vincularUsuarioUseCase.execute(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/academia/{academiaId}")
  public ResponseEntity<List<VinculoResponse>> listarPorAcademia(@PathVariable Long academiaId) {
    List<VinculoResponse> response = listarUsuariosPorAcademiaUseCase.execute(academiaId);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/personal/alunos")
  @IsPersonal
  public ResponseEntity<List<UsuarioResponse>> listarMeusAlunos() {
    List<UsuarioResponse> response = listarAlunosPorPersonalUseCase.execute();
    return ResponseEntity.ok(response);
  }
}
