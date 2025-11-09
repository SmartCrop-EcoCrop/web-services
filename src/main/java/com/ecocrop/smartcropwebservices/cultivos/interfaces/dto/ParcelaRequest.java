package com.ecocrop.smartcropwebservices.cultivos.interfaces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data; // IMPORTANTE: @Data de Lombok genera los getters y setters
import lombok.NoArgsConstructor;   // ¡Añadir!
import lombok.AllArgsConstructor;

@Data//
@NoArgsConstructor
@AllArgsConstructor
public class ParcelaRequest {
    private Long idParcela;

    @NotBlank(message = "El nombre de la parcela es obligatorio.")
    private String nombreParcela;

    @NotBlank(message = "El tipo de cultivo es obligatorio.")
    private String tipoCultivo;

    @NotNull(message = "El tamaño en hectáreas es obligatorio.")
    @Positive(message = "El tamaño debe ser un valor positivo.")
    private Double tamanoHa;

}