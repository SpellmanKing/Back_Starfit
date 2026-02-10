package com.starfit.service;

import com.starfit.dto.AssinaturaDTO;
import com.starfit.model.Assinatura;
import com.starfit.model.Plano;
import com.starfit.model.Usuario;
import com.starfit.repository.AssinaturaRepository;
import com.starfit.repository.PlanoRepository;
import com.starfit.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssinaturaService {

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PlanoRepository planoRepository;

    @Transactional
    public AssinaturaDTO criarAssinatura(Long usuarioId, Long planoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Plano plano = planoRepository.findById(planoId)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        // Cancela assinaturas ativas anteriores
        assinaturaRepository.findByUsuarioAndStatus(usuario, Assinatura.StatusAssinatura.ATIVA)
                .ifPresent(assinatura -> {
                    assinatura.setStatus(Assinatura.StatusAssinatura.CANCELADA);
                    assinatura.setDataFim(LocalDateTime.now());
                    assinaturaRepository.save(assinatura);
                });

        // Cria nova assinatura
        Assinatura assinatura = new Assinatura();
        assinatura.setUsuario(usuario);
        assinatura.setPlano(plano);
        assinatura.setDataInicio(LocalDateTime.now());
        assinatura.setStatus(Assinatura.StatusAssinatura.ATIVA);
        assinatura.setVisitasAmigosMes(0);

        // Define data de fim (30 dias)
        assinatura.setDataFim(LocalDateTime.now().plusDays(30));

        Assinatura saved = assinaturaRepository.save(assinatura);
        return convertToDTO(saved);
    }

    public AssinaturaDTO buscarAssinaturaAtivaPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Assinatura assinatura = assinaturaRepository.findByUsuarioAndStatus(
                usuario, Assinatura.StatusAssinatura.ATIVA)
                .orElseThrow(() -> new RuntimeException("Nenhuma assinatura ativa encontrada"));

        return convertToDTO(assinatura);
    }

    public List<AssinaturaDTO> listarAssinaturasPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return assinaturaRepository.findByUsuario(usuario).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void registrarVisitaAmigo(Long assinaturaId) {
        Assinatura assinatura = assinaturaRepository.findById(assinaturaId)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada"));

        if (!assinatura.getStatus().equals(Assinatura.StatusAssinatura.ATIVA)) {
            throw new RuntimeException("Assinatura não está ativa");
        }

        Plano plano = assinatura.getPlano();
        if (plano.getLevarAmigos() && plano.getLimiteAmigosMes() != null) {
            if (assinatura.getVisitasAmigosMes() >= plano.getLimiteAmigosMes()) {
                throw new RuntimeException("Limite de visitas de amigos atingido para este mês");
            }
            assinatura.setVisitasAmigosMes(assinatura.getVisitasAmigosMes() + 1);
            assinaturaRepository.save(assinatura);
        } else {
            throw new RuntimeException("Plano não permite levar amigos");
        }
    }

    private AssinaturaDTO convertToDTO(Assinatura assinatura) {
        AssinaturaDTO dto = new AssinaturaDTO();
        dto.setId(assinatura.getId());
        dto.setUsuarioId(assinatura.getUsuario().getId());
        dto.setPlanoId(assinatura.getPlano().getId());
        dto.setNomeUsuario(assinatura.getUsuario().getNome());
        dto.setNomePlano(assinatura.getPlano().getNome());
        dto.setDataInicio(assinatura.getDataInicio());
        dto.setDataFim(assinatura.getDataFim());
        dto.setStatus(assinatura.getStatus());
        dto.setVisitasAmigosMes(assinatura.getVisitasAmigosMes());
        return dto;
    }
}


