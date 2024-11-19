package com.web.parcial.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String identificador;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String nombreContratante;

    @Column(nullable = false)
    private String documentoContratante;

    @Column(nullable = false)
    private String nombreContratantista;

    @Column(nullable = false)
    private String documentoContratantista;

    @Column(nullable = false)
    private LocalDate fechaInicial;

    @Column(nullable = false)
    private LocalDate fechaFinal;
}
