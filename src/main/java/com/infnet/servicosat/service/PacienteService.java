package com.infnet.servicosat.service;

import com.infnet.servicosat.dto.request.PacienteRequestDTO;
import com.infnet.servicosat.dto.response.PacienteResponseDTO;
import com.infnet.servicosat.model.Paciente;
import com.infnet.servicosat.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteResponseDTO cadastrar(PacienteRequestDTO dto) {
        Paciente paciente = Paciente.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .dataNascimento(dto.getDataNascimento())
                .telefone(dto.getTelefone())
                .build();

        Paciente salvo = pacienteRepository.save(paciente);
        return toDTO(salvo);
    }

    public PacienteResponseDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado: " + id));
        return toDTO(paciente);
    }

    public List<PacienteResponseDTO> listar() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public void remover(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    private PacienteResponseDTO toDTO(Paciente p) {
        return PacienteResponseDTO.builder()
                .id(p.getId())
                .nome(p.getNome())
                .cpf(p.getCpf())
                .dataNascimento(p.getDataNascimento())
                .telefone(p.getTelefone())
                .build();
    }
}
