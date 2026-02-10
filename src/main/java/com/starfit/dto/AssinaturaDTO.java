package com.starfit.dto;

import com.starfit.model.Assinatura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssinaturaDTO {

    private Long id;
    private Long usuarioId;
    private Long planoId;
    private String nomeUsuario;
    private String nomePlano;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Assinatura.StatusAssinatura status;
    private Integer visitasAmigosMes;
}




