package com.ecocrop.smartcropwebservices.monitoreo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "dispositivo_iot")
public class DispositivoIot {

    // Usamos String para la PK, ya que los IDs IoT suelen ser MAC/Serial
    @Id
    private String idDispositivo;

    // Referencia simple a la Parcela (del Contexto de Cultivos)
    private Long idParcela;

    private String tipoDispositivo; // Ej: 'Sensor de Suelo', 'Estación Meteorológica'
    private String ubicacionEspecifica;
    private LocalDate fechaInstalacion;
    private String estado; // Ej: 'Activo', 'Desconectado'
}