package com.banco.blood.controller;

import com.banco.blood.model.Candidato;
import com.banco.blood.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    // Endpoint para importar candidatos
    @PostMapping("/importar")
    public void importarCandidatos(@RequestBody List<Candidato> candidatos) {
        candidatoService.salvarCandidatos(candidatos);
    }

    // Endpoint para obter candidatos por estado
    @GetMapping("/estadisticas")
    public List<Candidato> obterEstatisticas(@RequestParam String estado) {
        return candidatoService.obterCandidatosPorEstado(estado);
    }
}
