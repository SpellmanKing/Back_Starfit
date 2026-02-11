package com.starfit.service;

import com.starfit.dto.AuthResponseDTO;
import com.starfit.dto.LoginDTO;
import com.starfit.dto.UsuarioDTO;
import com.starfit.model.Assinatura;
import com.starfit.model.Usuario;
import com.starfit.repository.AssinaturaRepository;
import com.starfit.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public AuthResponseDTO login(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        if (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        if (!usuario.getAtivo()) {
            throw new RuntimeException("Usuário inativo");
        }

        String token = jwtService.generateToken(usuario.getEmail(), usuario.getId());

        // Verifica se tem assinatura ativa
        Optional<Assinatura> assinaturaAtiva = assinaturaRepository.findByUsuarioAndStatus(
                usuario, Assinatura.StatusAssinatura.ATIVA);

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        response.setUsuarioId(usuario.getId());
        response.setEmail(usuario.getEmail());
        response.setNome(usuario.getNome());
        response.setTemAssinaturaAtiva(assinaturaAtiva.isPresent());

        return response;
    }

    @Transactional
    public Usuario registrar(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (usuarioDTO.getCpf() != null && !usuarioDTO.getCpf().isEmpty()) {
            if (usuarioRepository.existsByCpf(usuarioDTO.getCpf())) {
                throw new RuntimeException("CPF já cadastrado");
            }
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        usuario.setNome(usuarioDTO.getNome());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setCpf(usuarioDTO.getCpf());

        return usuarioRepository.save(usuario);
    }
}








