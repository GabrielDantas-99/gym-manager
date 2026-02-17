package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.ExercicioResponse;
import com.gym.api.application.dto.FichaTreinoResponse;
import com.gym.api.domain.exception.BusinessException;
import com.gym.api.domain.repository.ExercicioRepository;
import com.gym.api.domain.repository.FichaTreinoRepository;
import com.gym.api.domain.repository.UsuarioRepository;
import com.gym.api.infraestructure.security.AuthenticatedUserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetFichasTreinoByAlunoUseCase {

  private final FichaTreinoRepository fichaTreinoRepository;
  private final ExercicioRepository exercicioRepository;
  private final UsuarioRepository usuarioRepository;
  private final AuthenticatedUserService authenticatedUserService;

  @Transactional(readOnly = true)
  public List<FichaTreinoResponse> execute(Long alunoId) {
    var usuarioAutenticado = authenticatedUserService.getAuthenticatedUser();

    if (usuarioAutenticado.isAluno() && !usuarioAutenticado.getId().equals(alunoId)) {
      throw new BusinessException("Você só pode visualizar suas próprias fichas de treino");
    }

    var fichas = fichaTreinoRepository.findByAlunoId(alunoId);

    return fichas.stream()
        .map(ficha -> {
          var aluno = usuarioRepository.findById(ficha.getAlunoId()).orElse(null);
          var personal = usuarioRepository.findById(ficha.getPersonalId()).orElse(null);
          var exercicios = exercicioRepository.findByFichaTreinoId(ficha.getId());

          return FichaTreinoResponse.builder()
              .id(ficha.getId())
              .alunoId(ficha.getAlunoId())
              .nomeAluno(aluno != null ? aluno.getNome() : null)
              .personalId(ficha.getPersonalId())
              .nomePersonal(personal != null ? personal.getNome() : null)
              .diaSemana(ficha.getDiaSemana())
              .grupoMuscular(ficha.getGrupoMuscular())
              .ativa(ficha.getAtiva())
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
              .dataCriacao(ficha.getDataCriacao())
              .build();
        })
        .collect(Collectors.toList());
  }
}
