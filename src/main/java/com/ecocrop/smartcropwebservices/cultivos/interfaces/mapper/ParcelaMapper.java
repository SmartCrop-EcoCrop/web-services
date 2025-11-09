package com.ecocrop.smartcropwebservices.cultivos.interfaces.mapper;

import com.ecocrop.smartcropwebservices.cultivos.domain.Parcela;
import com.ecocrop.smartcropwebservices.cultivos.interfaces.dto.ParcelaRequest;
import org.springframework.stereotype.Component;


@Component
public class ParcelaMapper {

    // Metodo para mapear el DTO a la Entidad
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


        return parcela;
    }

}