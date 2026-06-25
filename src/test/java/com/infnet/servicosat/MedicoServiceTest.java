package com.infnet.servicosat;

import com.infnet.servicosat.dto.request.MedicoRequestDTO;
import com.infnet.servicosat.dto.response.MedicoResponseDTO;
import com.infnet.servicosat.model.Medico;
import com.infnet.servicosat.repository.ConsultaRepository;
import com.infnet.servicosat.repository.MedicoRepository;
import com.infnet.servicosat.service.MedicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private MedicoService medicoService;

    private MedicoRequestDTO requestDTO;
    private Medico medico;

    @BeforeEach
    void setUp() {
        requestDTO = new MedicoRequestDTO(
                "Dr. Carlos Souza",
                "CRM-SP-123456",
                "Cardiologia"
        );

        medico = Medico.builder()
                .id(1L)
                .nome("Dr. Carlos Souza")
                .crm("CRM-SP-123456")
                .especialidade("Cardiologia")
                .build();
    }

    @Test
    @DisplayName("Deve cadastrar médico com sucesso")
    void deveCadastrarMedicoComSucesso() {
        when(medicoRepository.save(any(Medico.class))).thenReturn(medico);

        MedicoResponseDTO response = medicoService.cadastrar(requestDTO);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Dr. Carlos Souza", response.getNome());
        assertEquals("CRM-SP-123456", response.getCrm());
        assertEquals("Cardiologia", response.getEspecialidade());
        verify(medicoRepository, times(1)).save(any(Medico.class));
    }

    @Test
    @DisplayName("Deve listar médicos com sucesso")
    void deveListarMedicosComSucesso() {
        Medico medico2 = Medico.builder()
                .id(2L)
                .nome("Dra. Ana Lima")
                .crm("CRM-SP-654321")
                .especialidade("Ortopedia")
                .build();

        when(medicoRepository.findAll()).thenReturn(List.of(medico, medico2));

        List<MedicoResponseDTO> response = medicoService.listar();

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("Dr. Carlos Souza", response.get(0).getNome());
        assertEquals("Dra. Ana Lima", response.get(1).getNome());
        verify(medicoRepository, times(1)).findAll();
    }
}
