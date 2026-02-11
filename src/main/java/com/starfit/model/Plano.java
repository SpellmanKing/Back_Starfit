package com.starfit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "planos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome; // STAR BÁSICO, STAR PREMIUM

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "acesso_musculacao")
    private Boolean acessoMusculacao = false;

    @Column(name = "taxa_adesao")
    private Boolean taxaAdesao = false;

    @Column(name = "horario_livre")
    private Boolean horarioLivre = false;

    @Column(name = "acesso_outras_unidades")
    private Boolean acessoOutrasUnidades = false;

    @Column(name = "levar_amigos")
    private Boolean levarAmigos = false;

    @Column(name = "limite_amigos_mes")
    private Integer limiteAmigosMes = 0;

    @Column(name = "cadeira_massagem")
    private Boolean cadeiraMassagem = false;

    @Column(name = "spa_liberado")
    private Boolean spaLiberado = false;

    @Column(name = "acesso_ilimitado")
    private Boolean acessoIlimitado = false;

    @Column(name = "ativo")
    private Boolean ativo = true;
}








