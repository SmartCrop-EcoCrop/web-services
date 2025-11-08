package com.ecocrop.smartcropwebservices.cultivos.interfaces.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;   // ¡Añadir!
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FincaRequest {
    // Nota: El ID no se incluye para creación (POST)

    @NotNull(message = "El ID de usuario es obligatorio.")
    @Positive(message = "El ID de usuario debe ser positivo.")
    private Long idUsuario;

    @NotBlank(message = "El nombre de la finca no puede estar vacío.")
    private String nombreFinca;

    // Usaremos DTOs para UbicacionGeo
    @NotNull(message = "La ubicación geográfica es obligatoria.")
    private UbicacionGeoDto ubicacionGeo;

    @Valid // Valida cada elemento de la lista
    private List<ParcelaRequest> parcelas;
}