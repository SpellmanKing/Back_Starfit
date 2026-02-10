package com.starfit.service;

import com.starfit.dto.PlanoDTO;
import com.starfit.model.Plano;
import com.starfit.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    public List<PlanoDTO> listarPlanosAtivos() {
        return planoRepository.findByAtivoTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PlanoDTO buscarPorId(Long id) {
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
        return convertToDTO(plano);
    }

    public PlanoDTO buscarPorNome(String nome) {
        Plano plano = planoRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
        return convertToDTO(plano);
    }

    public void inicializarPlanos() {
        if (planoRepository.count() == 0) {
            // STAR BÁSICO
            Plano planoBasico = new Plano();
            planoBasico.setNome("STAR BÁSICO");
            planoBasico.setDescricao("Plano básico com acesso à musculação");
            planoBasico.setPreco(new BigDecimal("89.90"));
            planoBasico.setAcessoMusculacao(true);
            planoBasico.setTaxaAdesao(false);
            planoBasico.setHorarioLivre(true);
            planoBasico.setAcessoOutrasUnidades(true);
            planoBasico.setLevarAmigos(true);
            planoBasico.setLimiteAmigosMes(5);
            planoBasico.setCadeiraMassagem(false);
            planoBasico.setSpaLiberado(false);
            planoBasico.setAcessoIlimitado(false);
            planoBasico.setAtivo(true);
            planoRepository.save(planoBasico);

            // STAR PREMIUM
            Plano planoPremium = new Plano();
            planoPremium.setNome("STAR PREMIUM");
            planoPremium.setDescricao("Plano premium com acesso ilimitado e benefícios exclusivos");
            planoPremium.setPreco(new BigDecimal("119.90"));
            planoPremium.setAcessoMusculacao(true);
            planoPremium.setTaxaAdesao(false);
            planoPremium.setHorarioLivre(true);
            planoPremium.setAcessoOutrasUnidades(true);
            planoPremium.setLevarAmigos(true);
            planoPremium.setLimiteAmigosMes(5);
            planoPremium.setCadeiraMassagem(true);
            planoPremium.setSpaLiberado(true);
            planoPremium.setAcessoIlimitado(true);
            planoPremium.setAtivo(true);
            planoRepository.save(planoPremium);
        }
    }

    private PlanoDTO convertToDTO(Plano plano) {
        PlanoDTO dto = new PlanoDTO();
        dto.setId(plano.getId());
        dto.setNome(plano.getNome());
        dto.setDescricao(plano.getDescricao());
        dto.setPreco(plano.getPreco());
        dto.setAcessoMusculacao(plano.getAcessoMusculacao());
        dto.setTaxaAdesao(plano.getTaxaAdesao());
        dto.setHorarioLivre(plano.getHorarioLivre());
        dto.setAcessoOutrasUnidades(plano.getAcessoOutrasUnidades());
        dto.setLevarAmigos(plano.getLevarAmigos());
        dto.setLimiteAmigosMes(plano.getLimiteAmigosMes());
        dto.setCadeiraMassagem(plano.getCadeiraMassagem());
        dto.setSpaLiberado(plano.getSpaLiberado());
        dto.setAcessoIlimitado(plano.getAcessoIlimitado());
        dto.setAtivo(plano.getAtivo());
        return dto;
    }
}


