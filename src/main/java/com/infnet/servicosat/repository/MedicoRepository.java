package com.infnet.servicosat.repository;

import com.infnet.servicosat.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
