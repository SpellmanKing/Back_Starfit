package com.starfit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;
    private String tipo = "Bearer";
    private Long usuarioId;
    private String email;
    private String nome;
    private Boolean temAssinaturaAtiva;
}








