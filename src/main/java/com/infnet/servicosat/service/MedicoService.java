package com.infnet.servicosat.service;

import com.infnet.servicosat.dto.request.MedicoRequestDTO;
import com.infnet.servicosat.dto.response.MedicoRankingDTO;
import com.infnet.servicosat.dto.response.MedicoResponseDTO;
import com.infnet.servicosat.model.Medico;
import com.infnet.servicosat.repository.ConsultaRepository;
import com.infnet.servicosat.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final ConsultaRepository consultaRepository;

    public MedicoResponseDTO cadastrar(MedicoRequestDTO dto) {
        Medico medico = Medico.builder()
                .nome(dto.getNome())
                .crm(dto.getCrm())
                .especialidade(dto.getEspecialidade())
                .build();

        Medico salvo = medicoRepository.save(medico);
        return toDTO(salvo);
    }

    public List<MedicoResponseDTO> listar() {
        return medicoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private MedicoResponseDTO toDTO(Medico m) {
        return MedicoResponseDTO.builder()
                .id(m.getId())
                .nome(m.getNome())
                .crm(m.getCrm())
                .especialidade(m.getEspecialidade())
                .build();
    }

    public List<MedicoRankingDTO> rankingPorConsultas() {
        return consultaRepository.findMedicosRankedByConsultas().stream()
                .map(p -> MedicoRankingDTO.builder()
                        .id(p.getId())
                        .nome(p.getNome())
                        .crm(p.getCrm())
                        .especialidade(p.getEspecialidade())
                        .totalConsultas(p.getTotal())
                        .build())
                .toList();
    }
}
