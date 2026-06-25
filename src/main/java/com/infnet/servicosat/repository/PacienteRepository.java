package com.infnet.servicosat.repository;

import com.infnet.servicosat.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {}
