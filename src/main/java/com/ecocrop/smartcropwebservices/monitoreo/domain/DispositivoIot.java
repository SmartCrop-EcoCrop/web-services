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

    // Usamos string para que la pk ya que son dispositivos permita poner el modelo del dispositvio
    @Id
    private String idDispositivo;

    private Long idParcela;

    private String tipoDispositivo; // Ej: 'Sensor de Suelo', 'Estación Meteorológica'
    private String ubicacionEspecifica;
    private LocalDate fechaInstalacion;
    private String estado; // Ej: 'Activo', 'Desconectado'
}