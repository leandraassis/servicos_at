package com.infnet.servicosat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultaResponseDTO {
    private Long id;
    private Long pacienteId;
    private String nomePaciente;
    private Long medicoId;
    private String nomeMedico;
    private LocalDateTime dataConsulta;
    private String observacoes;
}

