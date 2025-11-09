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

    // Funcion para crear una tarea nueva (puede ser a mano o la genera el sistema)
    @Transactional
    public Tarea crearTarea(Tarea tarea) {
        // Podriamos poner una validacion aqui antes de guardar
        return tareaRepository.save(tarea);
    }

    // Trae todas las tareas que estan PENDIENTES para una finca especifica.
    @Transactional(readOnly = true)
    public List<Tarea> obtenerTareasPendientesPorFinca(Long idFinca) {
        // Asumimos que todo el 'to-do list' se organiza por Finca
        return tareaRepository.findByIdFinca(idFinca);
    }

    // Marcar como que ya se hizo (logica importante aqui)
    @Transactional
    public Tarea completarTarea(Long idTarea) {
        Tarea tarea = tareaRepository.findById(idTarea)
                .orElseThrow(() -> new RuntimeException("Tarea no se encontro: " + idTarea));

        // Revisamos si la tarea ya esta en un estado final. Por ejem, ya completada.
        if (tarea.getEstado() == EstadoTarea.COMPLETADA) {
            throw new IllegalStateException("La tarea ya estaba completada.");
        }

        tarea.setEstado(EstadoTarea.COMPLETADA);
        return tareaRepository.save(tarea);
    }

    @Transactional
    public Tarea marcarComoPendiente(Long idTarea) {
        Tarea tarea = tareaRepository.findById(idTarea)
                .orElseThrow(() -> new RuntimeException("Tarea no se encontro: " + idTarea));

        // Le cambiamos el estado para que vuelva a aparecer en la lista de cosas x hacer
        tarea.setEstado(EstadoTarea.PENDIENTE);
        return tareaRepository.save(tarea);
    }
}