package com.ecocrop.smartcropwebservices.cultivos.interfaces.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UbicacionGeoDto {
    @Min(value = -90, message = "Latitud mínima es -90.")
    @Max(value = 90, message = "Latitud máxima es 90.")
    private Double latitud;

    @Min(value = -180, message = "Longitud mínima es -180.")
    @Max(value = 180, message = "Longitud máxima es 180.")
    private Double longitud;
}