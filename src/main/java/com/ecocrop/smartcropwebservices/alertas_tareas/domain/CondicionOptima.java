package com.ecocrop.smartcropwebservices.alertas_tareas.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "condicion_optima")
public class CondicionOptima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recomendacion")
    private Long idRecomendacion;

    // Relación al TipoCultivo del contexto Cultivos (usamos String para evitar dependencia directa)
    private String tipoCultivo;

    // Reglas de alerta
    private Double tempMax; // Temperatura máxima
    private Double tempMin; // Temperatura mínima
    private Double humedadMin; // Humedad mínima del suelo

    // Regla de tarea
    private String tareaSugerida; // Ej: "Aplicar riego de emergencia"

    // Campo para el sensor o fuente de datos
    private String parametroMonitoreado; // Ej: "TemperaturaAire"
}