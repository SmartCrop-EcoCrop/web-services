package com.ecocrop.smartcropwebservices.cultivos.interfaces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FincaUpdateRequest {
    // idUsuario y nombreFinca son necesarios para actualizar

    @NotNull(message = "El ID de usuario es obligatorio.")
    @Positive(message = "El ID de usuario debe ser positivo.")
    private Long idUsuario;

    @NotBlank(message = "El nombre de la finca no puede estar vacío.")
    private String nombreFinca;

    // Usaremos DTOs para UbicacionGeo
    @NotNull(message = "La ubicación geográfica es obligatoria.")
    private UbicacionGeoDto ubicacionGeo;

}