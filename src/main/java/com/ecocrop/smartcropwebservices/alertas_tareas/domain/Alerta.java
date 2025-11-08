package com.ecocrop.smartcropwebservices.alertas_tareas.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlerta;

    // Relación al Contexto de Cultivos
    private Long idParcela;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_alerta")
    private TipoAlerta tipo;

    private String mensaje;
    private String valorMedido; // Ej: "32°C"

    private LocalDateTime FechaHoraInicio = LocalDateTime.now();

    private String Estado;
    private boolean activa = true;
}