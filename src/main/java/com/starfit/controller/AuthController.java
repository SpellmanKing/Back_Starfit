package com.starfit.controller;

import com.starfit.dto.AuthResponseDTO;
import com.starfit.dto.LoginDTO;
import com.starfit.dto.UsuarioDTO;
import com.starfit.model.Usuario;
import com.starfit.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            AuthResponseDTO response = authService.login(loginDTO);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = authService.registrar(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}


