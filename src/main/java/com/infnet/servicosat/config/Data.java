package com.infnet.servicosat.config;

import com.infnet.servicosat.model.Medico;
import com.infnet.servicosat.model.Paciente;
import com.infnet.servicosat.repository.MedicoRepository;
import com.infnet.servicosat.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Data implements CommandLineRunner {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    @Override
    public void run(String... args) {
        if (medicoRepository.count() == 0) {
            medicoRepository.saveAll(List.of(
                    Medico.builder().nome("Dra. Ellie Weller").crm("CRM-RJ-123456").especialidade("Cardiologia").build(),
                    Medico.builder()
                            .nome("Dr. Joel Moraes")
                            .crm("CRM-RJ-654321")
                            .especialidade("Ortopedia")
                            .build()
            ));
        }

        if (pacienteRepository.count() == 0) {
            pacienteRepository.saveAll(List.of(
                    Paciente.builder()
                            .nome("João Silva")
                            .cpf("12345678901")
                            .dataNascimento(LocalDate.of(1981, 2, 24))
                            .telefone("21999990001")
                            .build(),
                    Paciente.builder()
                            .nome("Maria Oliveira")
                            .cpf("98765432100")
                            .dataNascimento(LocalDate.of(2000, 7, 10))
                            .telefone("21999990002")
                            .build()
            ));
        }
    }
}