package com.infnet.servicosat;

import com.infnet.servicosat.dto.request.PacienteRequestDTO;
import com.infnet.servicosat.dto.response.PacienteResponseDTO;
import com.infnet.servicosat.model.Paciente;
import com.infnet.servicosat.repository.PacienteRepository;
import com.infnet.servicosat.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    private PacienteRequestDTO requestDTO;
    private Paciente paciente;

    @BeforeEach
    void setUp() {
        requestDTO = new PacienteRequestDTO(
                "João Silva",
                "12345678901",
                LocalDate.of(1985, 3, 22),
                "11999990001"
        );

        paciente = Paciente.builder()
                .id(1L)
                .nome("João Silva")
                .cpf("12345678901")
                .dataNascimento(LocalDate.of(1985, 3, 22))
                .telefone("11999990001")
                .build();
    }

    @Test
    @DisplayName("Deve cadastrar paciente com sucesso")
    void deveCadastrarPacienteComSucesso() {
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        PacienteResponseDTO response = pacienteService.cadastrar(requestDTO);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("João Silva", response.getNome());
        assertEquals("12345678901", response.getCpf());
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    @DisplayName("Deve buscar paciente por ID com sucesso")
    void deveBuscarPacientePorIdComSucesso() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        PacienteResponseDTO response = pacienteService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("João Silva", response.getNome());
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando paciente não encontrado por ID")
    void deveLancarExcecaoQuandoPacienteNaoEncontrado() {
        when(pacienteRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> pacienteService.buscarPorId(99L));

        assertEquals("Paciente não encontrado: 99", ex.getMessage());
        verify(pacienteRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Deve remover paciente com sucesso")
    void deveRemoverPacienteComSucesso() {
        when(pacienteRepository.existsById(1L)).thenReturn(true);
        doNothing().when(pacienteRepository).deleteById(1L);

        assertDoesNotThrow(() -> pacienteService.remover(1L));

        verify(pacienteRepository, times(1)).existsById(1L);
        verify(pacienteRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar remover paciente inexistente")
    void deveLancarExcecaoAoRemoverPacienteInexistente() {
        when(pacienteRepository.existsById(99L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> pacienteService.remover(99L));

        assertEquals("Paciente não encontrado: 99", ex.getMessage());
        verify(pacienteRepository, times(1)).existsById(99L);
        verify(pacienteRepository, never()).deleteById(any());
    }
}
