package com.infnet.servicosat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoRankingDTO {
    private Long id;
    private String nome;
    private String crm;
    private String especialidade;
    private Long totalConsultas;
}
