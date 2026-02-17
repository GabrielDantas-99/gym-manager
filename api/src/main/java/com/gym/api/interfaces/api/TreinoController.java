package com.gym.api.interfaces.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.api.application.dto.CreateFichaTreinoRequest;
import com.gym.api.application.dto.FichaTreinoResponse;
import com.gym.api.application.usecase.CreateFichaTreinoUseCase;
import com.gym.api.infraestructure.security.annotation.IsPersonal;

@RestController
@RequestMapping("/api/treinos")
@RequiredArgsConstructor
public class TreinoController {

  private final CreateFichaTreinoUseCase createFichaTreinoUseCase;

  @PostMapping("/fichas")
  @IsPersonal
  public ResponseEntity<FichaTreinoResponse> createFicha(@Valid @RequestBody CreateFichaTreinoRequest request) {
    FichaTreinoResponse response = createFichaTreinoUseCase.execute(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

}
