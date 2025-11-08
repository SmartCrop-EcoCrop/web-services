package com.ecocrop.smartcropwebservices.cultivos.interfaces.mapper;

import com.ecocrop.smartcropwebservices.cultivos.domain.Parcela;
import com.ecocrop.smartcropwebservices.cultivos.interfaces.dto.ParcelaRequest;
import org.springframework.stereotype.Component;

/**
 * Mapeador simple para convertir ParcelaRequest a Entidad Parcela.
 */
@Component // Lo registramos como un componente de Spring
public class ParcelaMapper {

    // Método para mapear el DTO a la Entidad
    public Parcela toEntity(ParcelaRequest dto) {
        if (dto == null) {
            return null;
        }

        Parcela parcela = new Parcela();

        // El ID es necesario para operaciones de actualización
        if (dto.getIdParcela() != null) {
            parcela.setIdParcela(dto.getIdParcela());
        }

        parcela.setNombreParcela(dto.getNombreParcela());
        parcela.setTipoCultivo(dto.getTipoCultivo());
        // Asegúrate de que tamanoHa esté en ParcelaRequest.java
        // parcela.setTamanoHa(dto.getTamanoHa());

        // NOTA: El campo 'finca' se establece en el FincaService, no aquí.

        return parcela;
    }

    // Aquí iría el método para mapear de Entidad a DTO de Respuesta si lo necesitaras:
    // public ParcelaResponse toDto(Parcela parcela) { ... }
}