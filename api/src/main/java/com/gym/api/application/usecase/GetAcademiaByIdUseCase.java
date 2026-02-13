package com.gym.api.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.api.application.dto.AcademiaResponse;
import com.gym.api.domain.exception.ResourceNotFoundException;
import com.gym.api.domain.repository.AcademiaRepository;

@Service
@RequiredArgsConstructor
public class GetAcademiaByIdUseCase {

    private final AcademiaRepository academiaRepository;

    @Transactional(readOnly = true)
    public AcademiaResponse execute(Long id) {
        var academia = academiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academia não encontrada"));

        return AcademiaResponse.builder()
                .id(academia.getId())
                .nome(academia.getNome())
                .endereco(academia.getEndereco())
                .telefone(academia.getTelefone())
                .adminId(academia.getAdminId())
                .ativa(academia.getAtiva())
                .dataCadastro(academia.getDataCadastro())
                .build();
    }
}
