package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.CreateFichaTreinoRequest;
import com.gym.api.application.dto.ExercicioResponse;
import com.gym.api.application.dto.FichaTreinoResponse;
import com.gym.api.domain.exception.BusinessException;
import com.gym.api.domain.exception.ResourceNotFoundException;
import com.gym.api.domain.model.Exercicio;
import com.gym.api.domain.model.FichaTreino;
import com.gym.api.domain.repository.ExercicioRepository;
import com.gym.api.domain.repository.FichaTreinoRepository;
import com.gym.api.domain.repository.UsuarioRepository;
import com.gym.api.domain.repository.VinculoRepository;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateFichaTreinoUseCase {

  private final FichaTreinoRepository fichaTreinoRepository;
  private final ExercicioRepository exercicioRepository;
  private final UsuarioRepository usuarioRepository;
  private final VinculoRepository vinculoRepository;
  private final AuthenticatedUserService authenticatedUserService;

  @Transactional
  public FichaTreinoResponse execute(CreateFichaTreinoRequest request) {
    var personal = authenticatedUserService.getAuthenticatedUser();

    if (!personal.isPersonal()) {
      throw new BusinessException("Apenas personais podem criar fichas de treino");
    }

    var aluno = usuarioRepository.findById(request.getAlunoId())
        .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

    if (!aluno.isAluno()) {
      throw new BusinessException("O usuário informado não é um aluno");
    }

    var vinculos = vinculoRepository.findByPersonalId(personal.getId());
    boolean alunoVinculado = vinculos.stream()
        .anyMatch(v -> v.getUsuarioId().equals(aluno.getId()));

    if (!alunoVinculado) {
      throw new BusinessException("Este aluno não está vinculado a você");
    }

    var fichaTreino = FichaTreino.builder()
        .alunoId(aluno.getId())
        .personalId(personal.getId())
        .diaSemana(request.getDiaSemana())
        .grupoMuscular(request.getGrupoMuscular())
        .ativa(true)
        .dataCriacao(LocalDateTime.now())
        .dataAtualizacao(LocalDateTime.now())
        .build();

    fichaTreino = fichaTreinoRepository.save(fichaTreino);

    final Long fichaTreinoId = fichaTreino.getId();

    List<Exercicio> exercicios = request.getExercicios().stream()
        .map(ex -> Exercicio.builder()
            .fichaTreinoId(fichaTreinoId)
            .nome(ex.getNome())
            .series(ex.getSeries())
            .repeticoes(ex.getRepeticoes())
            .observacoes(ex.getObservacoes())
            .ordem(ex.getOrdem())
            .build())
        .map(exercicioRepository::save)
        .collect(Collectors.toList());

    return FichaTreinoResponse.builder()
        .id(fichaTreino.getId())
        .alunoId(aluno.getId())
        .nomeAluno(aluno.getNome())
        .personalId(personal.getId())
        .nomePersonal(personal.getNome())
        .diaSemana(fichaTreino.getDiaSemana())
        .grupoMuscular(fichaTreino.getGrupoMuscular())
        .ativa(fichaTreino.getAtiva())
        .exercicios(exercicios.stream()
            .map(ex -> ExercicioResponse.builder()
                .id(ex.getId())
                .nome(ex.getNome())
                .series(ex.getSeries())
                .repeticoes(ex.getRepeticoes())
                .observacoes(ex.getObservacoes())
                .ordem(ex.getOrdem())
                .build())
            .collect(Collectors.toList()))
        .dataCriacao(fichaTreino.getDataCriacao())
        .build();
  }
}
