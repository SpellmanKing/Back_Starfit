package com.starfit.controller;

import com.starfit.dto.ContatoDTO;
import com.starfit.dto.NewsletterDTO;
import com.starfit.model.Contato;
import com.starfit.model.Newsletter;
import com.starfit.service.LandingPageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/landing")
@CrossOrigin(origins = "*")
public class LandingPageController {

    @Autowired
    private LandingPageService landingPageService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("API StarFit está funcionando!");
    }

    @PostMapping("/contato")
    public ResponseEntity<Contato> criarContato(@Valid @RequestBody ContatoDTO contatoDTO) {
        Contato contato = landingPageService.salvarContato(contatoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(contato);
    }

    @GetMapping("/contatos")
    public ResponseEntity<List<Contato>> listarContatos() {
        List<Contato> contatos = landingPageService.listarContatos();
        return ResponseEntity.ok(contatos);
    }

    @PostMapping("/newsletter")
    public ResponseEntity<Newsletter> inscreverNewsletter(@Valid @RequestBody NewsletterDTO newsletterDTO) {
        Newsletter newsletter = landingPageService.inscreverNewsletter(newsletterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newsletter);
    }

    @GetMapping("/newsletters")
    public ResponseEntity<List<Newsletter>> listarNewsletters() {
        List<Newsletter> newsletters = landingPageService.listarNewsletters();
        return ResponseEntity.ok(newsletters);
    }
}

