package com.ecocrop.smartcropwebservices.monitoreo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "registro_dato")
public class RegistroDato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistro;

    // FK al Dispositivo
    private String idDispositivo;

    private LocalDateTime fechaHora;

    // Campos de Medición
    private Double temperaturaAireCelsius;
    private Double humedadSueloPorcentaje;
    private Double humedadAirePorcentaje;
    private Double consumoEnergeticoKwh;
    private Double vientoKmh;
    private String pronosticoCielo;
    private Double presionHpa;
    private Double visibilidadKm;
    private Double precipitacionMm;
}