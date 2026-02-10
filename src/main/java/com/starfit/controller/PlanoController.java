package com.starfit.controller;

import com.starfit.dto.PlanoDTO;
import com.starfit.service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planos")
@CrossOrigin(origins = "*")
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    @GetMapping
    public ResponseEntity<List<PlanoDTO>> listarPlanos() {
        List<PlanoDTO> planos = planoService.listarPlanosAtivos();
        return ResponseEntity.ok(planos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> buscarPorId(@PathVariable Long id) {
        try {
            PlanoDTO plano = planoService.buscarPorId(id);
            return ResponseEntity.ok(plano);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<PlanoDTO> buscarPorNome(@PathVariable String nome) {
        try {
            PlanoDTO plano = planoService.buscarPorNome(nome);
            return ResponseEntity.ok(plano);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}




