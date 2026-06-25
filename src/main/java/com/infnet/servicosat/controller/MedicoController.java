package com.infnet.servicosat.controller;

import com.infnet.servicosat.dto.request.MedicoRequestDTO;
import com.infnet.servicosat.dto.response.MedicoRankingDTO;
import com.infnet.servicosat.dto.response.MedicoResponseDTO;
import com.infnet.servicosat.service.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> cadastrar(@RequestBody @Valid MedicoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> listar() {
        return ResponseEntity.ok(medicoService.listar());
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<MedicoRankingDTO>> ranking() {
        return ResponseEntity.ok(medicoService.rankingPorConsultas());
    }
}
