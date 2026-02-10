package com.starfit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "newsletters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "data_inscricao")
    private LocalDateTime dataInscricao;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @PrePersist
    protected void onCreate() {
        dataInscricao = LocalDateTime.now();
        ativo = true;
    }
}

