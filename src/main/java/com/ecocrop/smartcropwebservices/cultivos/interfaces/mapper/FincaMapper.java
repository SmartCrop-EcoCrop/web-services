package com.ecocrop.smartcropwebservices.cultivos.interfaces.mapper;

import com.ecocrop.smartcropwebservices.cultivos.domain.Finca;
import com.ecocrop.smartcropwebservices.cultivos.domain.UbicacionGeo;
import com.ecocrop.smartcropwebservices.cultivos.interfaces.dto.FincaRequest;
import com.ecocrop.smartcropwebservices.cultivos.interfaces.dto.UbicacionGeoDto;
import com.ecocrop.smartcropwebservices.cultivos.domain.Parcela; // Importar Parcela
import org.springframework.beans.factory.annotation.Autowired; // Importar Autowired
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FincaMapper {

    // NUEVO: Inyección de ParcelaMapper
    private final ParcelaMapper parcelaMapper;

    @Autowired
    public FincaMapper(ParcelaMapper parcelaMapper) {
        this.parcelaMapper = parcelaMapper;
    }

    // Método principal de mapeo (toEntity)
    public Finca toEntity(FincaRequest dto) {
        Finca finca = new Finca();
        // ... (resto del código) ...

        if (dto.getUbicacionGeo() != null) {
            finca.setUbicacionGeo(toUbicacionGeo(dto.getUbicacionGeo()));
        }

        if (dto.getParcelas() != null) {
            finca.setParcelas(dto.getParcelas().stream()
                    // CAMBIO AQUÍ: Usamos el objeto inyectado 'parcelaMapper'
                    .map(pDto -> parcelaMapper.toEntity(pDto))
                    .collect(Collectors.toList()));
        }
        return finca;
    }

    private UbicacionGeo toUbicacionGeo(UbicacionGeoDto dto) {
        return new UbicacionGeo(dto.getLatitud(), dto.getLongitud());
    }

    // (Deberías crear también toDto para mapear Finca -> FincaResponse DTO)


}