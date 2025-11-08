package com.ecocrop.smartcropwebservices.alertas_tareas.application;

import com.ecocrop.smartcropwebservices.alertas_tareas.domain.EstadoTarea;
import com.ecocrop.smartcropwebservices.alertas_tareas.domain.Tarea;
import com.ecocrop.smartcropwebservices.alertas_tareas.infrastructure.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    @Autowired
    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    // CREATE: Crea una nueva tarea (ej. puede ser generada manualmente por el usuario o por el sistema)
    @Transactional
    public Tarea crearTarea(Tarea tarea) {
        // Lógica de validación inicial
        return tareaRepository.save(tarea);
    }

    // READ: Obtiene todas las tareas pendientes para una finca específica
    @Transactional(readOnly = true)
    public List<Tarea> obtenerTareasPendientesPorFinca(Long idFinca) {
        // En este caso, asumimos que todas las tareas en el to-do list se agrupan por Finca
        return tareaRepository.findByIdFinca(idFinca);
    }

    // UPDATE: Marca una tarea como completada (Lógica de Dominio)
    @Transactional
    public Tarea completarTarea(Long idTarea) {
        Tarea tarea = tareaRepository.findById(idTarea)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada: " + idTarea));

        // Aplicamos lógica de dominio para asegurar que no se complete una tarea cancelada, etc.
        if (tarea.getEstado() == EstadoTarea.COMPLETADA) {
            throw new IllegalStateException("La tarea ya estaba completada.");
        }

        tarea.setEstado(EstadoTarea.COMPLETADA);
        return tareaRepository.save(tarea);
    }

    @Transactional
    public Tarea marcarComoPendiente(Long idTarea) {
        Tarea tarea = tareaRepository.findById(idTarea)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada: " + idTarea));

        // Lógica de dominio: La tarea vuelve a estar activa
        tarea.setEstado(EstadoTarea.PENDIENTE);
        return tareaRepository.save(tarea);
    }
}