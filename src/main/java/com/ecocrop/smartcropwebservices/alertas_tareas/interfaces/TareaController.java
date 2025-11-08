package com.ecocrop.smartcropwebservices.alertas_tareas.interfaces;

import com.ecocrop.smartcropwebservices.alertas_tareas.application.TareaService;
import com.ecocrop.smartcropwebservices.alertas_tareas.domain.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.List;

@RestController
@RequestMapping("/api/v1/tareas")
@Tag(name = "Tarea")
public class TareaController {

    private final TareaService tareaService;

    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    /**
     * GET: Obtiene la lista de tareas pendientes para mostrar en el dashboard.
     * @param idFinca ID de la Finca del agricultor (asumimos que viene del usuario logueado).
     * @return Lista de Tareas.
     */
    @GetMapping("/finca/{idFinca}")
    public ResponseEntity<List<Tarea>> obtenerTareasPendientes(@PathVariable Long idFinca) {
        List<Tarea> tareas = tareaService.obtenerTareasPendientesPorFinca(idFinca);
        return ResponseEntity.ok(tareas);
    }

    /**
     * POST: Permite al usuario o a otro servicio crear una nueva tarea.
     * @param tarea Objeto Tarea a crear.
     * @return La Tarea creada.
     */
    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = tareaService.crearTarea(tarea);
        return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
    }

    /**
     * PUT: Marca una tarea como completada (funcionalidad clave del to-do list).
     * @param idTarea ID de la Tarea a completar.
     * @return La Tarea actualizada.
     */
    @PutMapping("/{idTarea}/completar")
    public ResponseEntity<Tarea> completarTarea(@PathVariable Long idTarea) {
        Tarea tareaCompletada = tareaService.completarTarea(idTarea);
        return ResponseEntity.ok(tareaCompletada);
    }

    /**
     * PUT: Marca una tarea como PENDIENTE.
     */
    @PutMapping("/{idTarea}/pendiente")
    public ResponseEntity<Tarea> marcarPendiente(@PathVariable Long idTarea) {
        Tarea tareaPendiente = tareaService.marcarComoPendiente(idTarea);
        return ResponseEntity.ok(tareaPendiente);
    }
}