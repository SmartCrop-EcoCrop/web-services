package com.ecocrop.smartcropwebservices.cultivos.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; // ¡Correcto!

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionGeo {
    private Double latitud;
    private Double longitud;
}