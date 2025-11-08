package com.ecocrop.smartcropwebservices.cultivos.interfaces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data; // IMPORTANTE: @Data de Lombok genera los getters y setters
import lombok.NoArgsConstructor;   // ¡Añadir!
import lombok.AllArgsConstructor;

@Data// <-- Esta anotación genera getTipoCultivo()
@NoArgsConstructor
@AllArgsConstructor
public class ParcelaRequest {
    private Long idParcela; // Incluido para actualizaciones

    @NotBlank(message = "El nombre de la parcela es obligatorio.")
    private String nombreParcela;

    // ¡CAMPO FALTANTE!
    @NotBlank(message = "El tipo de cultivo es obligatorio.")
    private String tipoCultivo;

    // También asegúrate de incluir tamanoHa y otros campos que se usan en ParcelaMapper
    @NotNull(message = "El tamaño en hectáreas es obligatorio.")
    @Positive(message = "El tamaño debe ser un valor positivo.")
    private Double tamanoHa;

    // ... otros campos si los necesitas ...
}