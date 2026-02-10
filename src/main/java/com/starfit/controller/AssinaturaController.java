package com.starfit.controller;

import com.starfit.dto.AssinaturaDTO;
import com.starfit.service.AssinaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assinaturas")
@CrossOrigin(origins = "*")
public class AssinaturaController {

    @Autowired
    private AssinaturaService assinaturaService;

    @PostMapping
    public ResponseEntity<AssinaturaDTO> criarAssinatura(@RequestBody Map<String, Long> request) {
        try {
            Long usuarioId = request.get("usuarioId");
            Long planoId = request.get("planoId");
            AssinaturaDTO assinatura = assinaturaService.criarAssinatura(usuarioId, planoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(assinatura);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/usuario/{usuarioId}/ativa")
    public ResponseEntity<AssinaturaDTO> buscarAssinaturaAtiva(@PathVariable Long usuarioId) {
        try {
            AssinaturaDTO assinatura = assinaturaService.buscarAssinaturaAtivaPorUsuario(usuarioId);
            return ResponseEntity.ok(assinatura);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AssinaturaDTO>> listarAssinaturas(@PathVariable Long usuarioId) {
        try {
            List<AssinaturaDTO> assinaturas = assinaturaService.listarAssinaturasPorUsuario(usuarioId);
            return ResponseEntity.ok(assinaturas);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{assinaturaId}/visita-amigo")
    public ResponseEntity<String> registrarVisitaAmigo(@PathVariable Long assinaturaId) {
        try {
            assinaturaService.registrarVisitaAmigo(assinaturaId);
            return ResponseEntity.ok("Visita de amigo registrada com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}




