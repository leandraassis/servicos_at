package com.infnet.servicosat.service;

import com.infnet.servicosat.dto.request.ConsultaRequestDTO;
import com.infnet.servicosat.dto.response.ConsultaResponseDTO;
import com.infnet.servicosat.model.Consulta;
import com.infnet.servicosat.model.Medico;
import com.infnet.servicosat.model.Paciente;
import com.infnet.servicosat.repository.ConsultaRepository;
import com.infnet.servicosat.repository.MedicoRepository;
import com.infnet.servicosat.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaResponseDTO cadastrar(ConsultaRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado: " + dto.getPacienteId()));

        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado: " + dto.getMedicoId()));

        Consulta consulta = Consulta.builder()
                .paciente(paciente)
                .medico(medico)
                .dataConsulta(dto.getDataConsulta())
                .observacoes(dto.getObservacoes())
                .build();

        Consulta salva = consultaRepository.save(consulta);
        return toDTO(salva);
    }

    private ConsultaResponseDTO toDTO(Consulta c) {
        return ConsultaResponseDTO.builder()
                .id(c.getId())
                .pacienteId(c.getPaciente().getId())
                .nomePaciente(c.getPaciente().getNome())
                .medicoId(c.getMedico().getId())
                .nomeMedico(c.getMedico().getNome())
                .dataConsulta(c.getDataConsulta())
                .observacoes(c.getObservacoes())
                .build();
    }
}
