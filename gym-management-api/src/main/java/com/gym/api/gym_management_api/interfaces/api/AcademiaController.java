package com.gym.api.gym_management_api.interfaces.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.api.gym_management_api.application.dto.AcademiaResponse;
import com.gym.api.gym_management_api.application.dto.CreateAcademiaRequest;
import com.gym.api.gym_management_api.application.usecase.CreateAcademiaUseCase;
import com.gym.api.gym_management_api.application.usecase.GetAcademiaByIdUseCase;
import com.gym.api.gym_management_api.application.usecase.ListAcademiasUseCase;
import com.gym.api.gym_management_api.infraestructure.security.annotation.IsAdmin;

import java.util.List;

@RestController
@RequestMapping("/api/academias")
@RequiredArgsConstructor
public class AcademiaController {

  private final CreateAcademiaUseCase createAcademiaUseCase;
  private final GetAcademiaByIdUseCase getAcademiaByIdUseCase;
  private final ListAcademiasUseCase listAcademiasUseCase;

  @PostMapping
  @IsAdmin
  public ResponseEntity<AcademiaResponse> create(@Valid @RequestBody CreateAcademiaRequest request) {
    AcademiaResponse response = createAcademiaUseCase.execute(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AcademiaResponse> getById(@PathVariable Long id) {
    AcademiaResponse response = getAcademiaByIdUseCase.execute(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<List<AcademiaResponse>> list() {
    List<AcademiaResponse> response = listAcademiasUseCase.execute();
    return ResponseEntity.ok(response);
  }
}
