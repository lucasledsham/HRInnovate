package com.tis2.AppRh.entities.indicadores;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Indicador_PorcentagemUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndicadorPorcentagemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer usuariosCadastrados;
    private Double porcentagem;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();
}
