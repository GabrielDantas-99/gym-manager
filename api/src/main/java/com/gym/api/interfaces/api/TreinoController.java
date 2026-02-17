package com.gym.api.interfaces.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.api.application.dto.CreateFichaTreinoRequest;
import com.gym.api.application.dto.FichaTreinoResponse;
import com.gym.api.application.usecase.CreateFichaTreinoUseCase;
import com.gym.api.application.usecase.GetFichasTreinoByAlunoUseCase;
import com.gym.api.application.usecase.UpdateFichaTreinoUseCase;
import com.gym.api.infraestructure.security.annotation.IsPersonal;

import java.util.List;

@RestController
@RequestMapping("/api/treinos")
@RequiredArgsConstructor
public class TreinoController {

  private final CreateFichaTreinoUseCase createFichaTreinoUseCase;
  private final GetFichasTreinoByAlunoUseCase getFichasTreinoByAlunoUseCase;
  private final UpdateFichaTreinoUseCase updateFichaTreinoUseCase;

  @PostMapping("/fichas")
  @IsPersonal
  public ResponseEntity<FichaTreinoResponse> createFicha(@Valid @RequestBody CreateFichaTreinoRequest request) {
    FichaTreinoResponse response = createFichaTreinoUseCase.execute(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/fichas/aluno/{alunoId}")
  public ResponseEntity<List<FichaTreinoResponse>> getFichasByAluno(@PathVariable Long alunoId) {
    List<FichaTreinoResponse> response = getFichasTreinoByAlunoUseCase.execute(alunoId);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/fichas/{id}")
  @IsPersonal
  public ResponseEntity<FichaTreinoResponse> updateFicha(
      @PathVariable Long id,
      @Valid @RequestBody CreateFichaTreinoRequest request) {
    FichaTreinoResponse response = updateFichaTreinoUseCase.execute(id, request);
    return ResponseEntity.ok(response);
  }

}
