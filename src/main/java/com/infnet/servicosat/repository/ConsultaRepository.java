package com.infnet.servicosat.repository;

import com.infnet.servicosat.model.Consulta;
import com.infnet.servicosat.repository.projection.MedicoRankingProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("""
        SELECT c.medico.id, c.medico.nome, c.medico.crm, c.medico.especialidade, COUNT(c) as total
        FROM Consulta c
        GROUP BY c.medico.id, c.medico.nome, c.medico.crm, c.medico.especialidade
        ORDER BY total DESC
    """)
    List<MedicoRankingProjection> findMedicosRankedByConsultas();
}
