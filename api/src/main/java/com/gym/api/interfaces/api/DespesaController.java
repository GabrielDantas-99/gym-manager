package com.gym.api.interfaces.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.api.application.dto.CreateDespesaRequest;
import com.gym.api.application.dto.DespesaResponse;
import com.gym.api.application.usecase.CreateDespesaUseCase;
import com.gym.api.application.usecase.ListarDespesasUseCase;
import com.gym.api.infraestructure.security.annotation.IsAdmin;

import java.util.List;

@RestController
@RequestMapping("/api/financeiro/despesas")
@RequiredArgsConstructor
public class DespesaController {

  private final CreateDespesaUseCase createDespesaUseCase;
  private final ListarDespesasUseCase listarDespesasUseCase;

  @PostMapping
  @IsAdmin
  public ResponseEntity<DespesaResponse> create(@Valid @RequestBody CreateDespesaRequest request) {
    DespesaResponse response = createDespesaUseCase.execute(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping
  @IsAdmin
  public ResponseEntity<List<DespesaResponse>> listar() {
    List<DespesaResponse> response = listarDespesasUseCase.execute();
    return ResponseEntity.ok(response);
  }
}
