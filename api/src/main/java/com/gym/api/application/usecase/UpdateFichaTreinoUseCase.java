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
import com.gym.api.domain.repository.ExercicioRepository;
import com.gym.api.domain.repository.FichaTreinoRepository;
import com.gym.api.domain.repository.UsuarioRepository;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateFichaTreinoUseCase {

  private final FichaTreinoRepository fichaTreinoRepository;
  private final ExercicioRepository exercicioRepository;
  private final UsuarioRepository usuarioRepository;
  private final AuthenticatedUserService authenticatedUserService;

  @Transactional
  public FichaTreinoResponse execute(Long id, CreateFichaTreinoRequest request) {
    var personal = authenticatedUserService.getAuthenticatedUser();

    if (!personal.isPersonal()) {
      throw new BusinessException("Apenas personais podem editar fichas de treino");
    }

    var fichaTreino = fichaTreinoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Ficha de treino não encontrada"));

    if (!fichaTreino.getPersonalId().equals(personal.getId())) {
      throw new BusinessException("Você só pode editar fichas criadas por você");
    }

    fichaTreino.setDiaSemana(request.getDiaSemana());
    fichaTreino.setGrupoMuscular(request.getGrupoMuscular());
    fichaTreino.setDataAtualizacao(LocalDateTime.now());

    fichaTreino = fichaTreinoRepository.save(fichaTreino);

    exercicioRepository.deleteByFichaTreinoId(fichaTreino.getId());

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

    var aluno = usuarioRepository.findById(fichaTreino.getAlunoId()).orElse(null);

    return FichaTreinoResponse.builder()
        .id(fichaTreino.getId())
        .alunoId(fichaTreino.getAlunoId())
        .nomeAluno(aluno != null ? aluno.getNome() : null)
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
