package com.ecocrop.smartcropwebservices.monitoreo.infrastructure;

import com.ecocrop.smartcropwebservices.monitoreo.domain.RegistroDato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroDatoRepository extends JpaRepository<RegistroDato, Long> {

    // Consulta para el historial (ej: últimas 24 horas o 7 días)
    List<RegistroDato> findByIdDispositivoAndFechaHoraBetweenOrderByFechaHoraAsc(
            String idDispositivo, LocalDateTime inicio, LocalDateTime fin);

    // Obtener la última lectura de un dispositivo
    RegistroDato findFirstByIdDispositivoOrderByFechaHoraDesc(String idDispositivo);
}