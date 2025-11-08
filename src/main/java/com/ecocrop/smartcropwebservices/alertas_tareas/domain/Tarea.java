package com.ecocrop.smartcropwebservices.alertas_tareas.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarea;

    // Relación al Contexto de Cultivos (solo mantenemos la referencia ID)
    private Long idFinca;
    private Long idParcela;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoTarea estado = EstadoTarea.PENDIENTE; // Por defecto

    private LocalDate fechaCreacion = LocalDate.now();
    private LocalDate fechaVencimiento;

    // Campo que podría usarse para la programación de tareas (ej. "Diario", "Semanal")
    private String recurrencia;
}