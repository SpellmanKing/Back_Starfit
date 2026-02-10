package com.starfit.service;

import com.starfit.dto.ContatoDTO;
import com.starfit.dto.NewsletterDTO;
import com.starfit.model.Contato;
import com.starfit.model.Newsletter;
import com.starfit.repository.ContatoRepository;
import com.starfit.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandingPageService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private NewsletterRepository newsletterRepository;

    public Contato salvarContato(ContatoDTO contatoDTO) {
        Contato contato = new Contato();
        contato.setNome(contatoDTO.getNome());
        contato.setEmail(contatoDTO.getEmail());
        contato.setTelefone(contatoDTO.getTelefone());
        contato.setMensagem(contatoDTO.getMensagem());
        return contatoRepository.save(contato);
    }

    public List<Contato> listarContatos() {
        return contatoRepository.findAll();
    }

    public Newsletter inscreverNewsletter(NewsletterDTO newsletterDTO) {
        // Verifica se o email já está cadastrado
        if (newsletterRepository.existsByEmail(newsletterDTO.getEmail())) {
            throw new RuntimeException("Email já cadastrado na newsletter");
        }

        Newsletter newsletter = new Newsletter();
        newsletter.setEmail(newsletterDTO.getEmail());
        return newsletterRepository.save(newsletter);
    }

    public List<Newsletter> listarNewsletters() {
        return newsletterRepository.findAll();
    }
}

