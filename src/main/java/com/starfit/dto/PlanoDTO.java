package com.starfit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean acessoMusculacao;
    private Boolean taxaAdesao;
    private Boolean horarioLivre;
    private Boolean acessoOutrasUnidades;
    private Boolean levarAmigos;
    private Integer limiteAmigosMes;
    private Boolean cadeiraMassagem;
    private Boolean spaLiberado;
    private Boolean acessoIlimitado;
    private Boolean ativo;
}








