package com.ecocrop.smartcropwebservices.alertas_tareas.infrastructure;

import com.ecocrop.smartcropwebservices.alertas_tareas.domain.Tarea;
import com.ecocrop.smartcropwebservices.alertas_tareas.domain.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    // Función de búsqueda común: Obtener todas las tareas pendientes de una Parcela
    List<Tarea> findByIdParcelaAndEstado(Long idParcela, EstadoTarea estado);

    // Buscar todas las tareas para un usuario/finca
    List<Tarea> findByIdFinca(Long idFinca);
}