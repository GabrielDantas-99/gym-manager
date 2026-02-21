package com.gym.api.interfaces.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.api.application.dto.DashboardResponse;
import com.gym.api.application.usecase.GetDashboardUseCase;
import com.gym.api.infraestructure.security.annotation.IsAdmin;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

  private final GetDashboardUseCase getDashboardUseCase;

  @GetMapping
  @IsAdmin
  public ResponseEntity<DashboardResponse> getDashboard() {
    DashboardResponse response = getDashboardUseCase.execute();
    return ResponseEntity.ok(response);
  }
}
