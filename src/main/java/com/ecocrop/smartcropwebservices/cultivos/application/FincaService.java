package com.ecocrop.smartcropwebservices.cultivos.application;

import com.ecocrop.smartcropwebservices.cultivos.domain.Finca;
import com.ecocrop.smartcropwebservices.cultivos.domain.Parcela;
import com.ecocrop.smartcropwebservices.cultivos.domain.UbicacionGeo; // Importar UbicacionGeo
import com.ecocrop.smartcropwebservices.cultivos.infrastructure.FincaRepository;
import com.ecocrop.smartcropwebservices.cultivos.interfaces.dto.FincaRequest;
import com.ecocrop.smartcropwebservices.cultivos.interfaces.dto.FincaUpdateRequest;
import com.ecocrop.smartcropwebservices.cultivos.interfaces.dto.UbicacionGeoDto; // Importar DTO
import com.ecocrop.smartcropwebservices.cultivos.interfaces.mapper.FincaMapper;
import com.ecocrop.smartcropwebservices.cultivos.infrastructure.ParcelaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FincaService {

    private final FincaRepository fincaRepository;
    private final ParcelaRepository parcelaRepository;
    private final FincaMapper fincaMapper;

    @Autowired
    public FincaService(FincaRepository fincaRepository, FincaMapper fincaMapper, ParcelaRepository parcelaRepository) {
        this.fincaRepository = fincaRepository;
        this.parcelaRepository = parcelaRepository;
        this.fincaMapper = fincaMapper;
    }

    // CREATE: (Este método está correcto para la creación con colecciones)
    @Transactional
    public Finca crearFincaDesdeDto(FincaRequest request) {
        Finca nuevaFinca = fincaMapper.toEntity(request);
        if (nuevaFinca.getParcelas() != null) {
            for (Parcela parcela : nuevaFinca.getParcelas()) {
                parcela.setFinca(nuevaFinca);
            }
        }
        return fincaRepository.save(nuevaFinca);
    }

    // READ: (Estos métodos están correctos)
    @Transactional(readOnly = true)
    public List<Finca> obtenerFincasPorUsuario(Long idUsuario) {
        return fincaRepository.findByIdUsuario(idUsuario);
    }

    @Transactional(readOnly = true)
    public Finca obtenerFincaPorId(Long idFinca) {
        return fincaRepository.findById(idFinca)
                .orElseThrow(() -> new RuntimeException("Finca no encontrada: " + idFinca));
    }


    // UPDATE: Actualizar una Finca (ACEPTA EL DTO SIMPLE)
    @Transactional
    public Finca actualizarFinca(Long idFinca, FincaUpdateRequest request) { // <-- CAMBIO AQUÍ
        Finca fincaExistente = obtenerFincaPorId(idFinca);

        // 1. Actualizar campos simples
        fincaExistente.actualizarNombre(request.getNombreFinca());
        fincaExistente.setIdUsuario(request.getIdUsuario());

        // 2. Actualizar Objeto de Valor (UbicacionGeo)
        UbicacionGeoDto geoDto = request.getUbicacionGeo();
        if (geoDto != null) {
            UbicacionGeo nuevaUbicacion = new UbicacionGeo(geoDto.getLatitud(), geoDto.getLongitud());
            fincaExistente.setUbicacionGeo(nuevaUbicacion);
        }

        // ¡No hay lista de Parcelas que cause conflicto!

        return fincaRepository.save(fincaExistente);
    }

    // DELETE: (Este método está correcto)
    @Transactional
    public void eliminarFinca(Long idFinca) {
        Finca finca = obtenerFincaPorId(idFinca);
        fincaRepository.delete(finca);
    }

    //  Exponer la Parcela a otros contextos por su ID
    @Transactional(readOnly = true)
    public Parcela obtenerParcelaPorId(Long idParcela) {
        return parcelaRepository.findById(idParcela)
                .orElseThrow(() -> new RuntimeException("Parcela no encontrada: " + idParcela));
    }
}