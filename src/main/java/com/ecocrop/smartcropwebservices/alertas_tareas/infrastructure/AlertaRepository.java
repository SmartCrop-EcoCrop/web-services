package com.ecocrop.smartcropwebservices.alertas_tareas.infrastructure;

import com.ecocrop.smartcropwebservices.alertas_tareas.domain.Alerta;
import com.ecocrop.smartcropwebservices.alertas_tareas.domain.TipoAlerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    // Función de búsqueda: Obtener todas las alertas activas para una Parcela
    List<Alerta> findByIdParcelaAndActiva(Long idParcela, boolean activa);

    // Obtener alertas por tipo (ej: solo alertas de "Temperatura Alta")
    List<Alerta> findByTipoAndIdParcela(TipoAlerta tipo, Long idParcela);
}