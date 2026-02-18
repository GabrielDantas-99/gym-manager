package com.gym.api.interfaces.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.api.application.dto.CreateMensalidadeRequest;
import com.gym.api.application.dto.MensalidadeResponse;
import com.gym.api.application.usecase.CreateMensalidadeUseCase;
import com.gym.api.application.usecase.ListarMensalidadesPorAlunoUseCase;
import com.gym.api.application.usecase.ListarTodasMensalidadesUseCase;
import com.gym.api.application.usecase.RegistrarPagamentoUseCase;
import com.gym.api.infraestructure.security.annotation.IsAdmin;

import java.util.List;

@RestController
@RequestMapping("/api/financeiro/mensalidades")
@RequiredArgsConstructor
public class MensalidadeController {

  private final CreateMensalidadeUseCase createMensalidadeUseCase;
  private final RegistrarPagamentoUseCase registrarPagamentoUseCase;
  private final ListarMensalidadesPorAlunoUseCase listarMensalidadesPorAlunoUseCase;
  private final ListarTodasMensalidadesUseCase listarTodasMensalidadesUseCase;

  @PostMapping
  @IsAdmin
  public ResponseEntity<MensalidadeResponse> create(@Valid @RequestBody CreateMensalidadeRequest request) {
    MensalidadeResponse response = createMensalidadeUseCase.execute(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{id}/pagar")
  @IsAdmin
  public ResponseEntity<MensalidadeResponse> registrarPagamento(@PathVariable Long id) {
    MensalidadeResponse response = registrarPagamentoUseCase.execute(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/aluno/{alunoId}")
  public ResponseEntity<List<MensalidadeResponse>> listarPorAluno(@PathVariable Long alunoId) {
    List<MensalidadeResponse> response = listarMensalidadesPorAlunoUseCase.execute(alunoId);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  @IsAdmin
  public ResponseEntity<List<MensalidadeResponse>> listarTodas() {
    List<MensalidadeResponse> response = listarTodasMensalidadesUseCase.execute();
    return ResponseEntity.ok(response);
  }
}
