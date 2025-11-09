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
     * Trae la lista de las cosas que hay que hacer para mostrar en el panel principal.
     * @param idFinca ID de la Finca (asumimos que sabemos quien es el usuario que entro).
     * @return Lista de Tareas pendientes.
     */
    @GetMapping("/finca/{idFinca}")
    public ResponseEntity<List<Tarea>> obtenerTareasPendientes(@PathVariable Long idFinca) {
        List<Tarea> tareas = tareaService.obtenerTareasPendientesPorFinca(idFinca);
        return ResponseEntity.ok(tareas);
    }

    /**
     * Permite crear una nueva tarea (sea el usuario o el sistema automaticamente).
     * @param tarea El objeto Tarea con la info.
     * @return La Tarea que se acaba de guardar.
     */
    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = tareaService.crearTarea(tarea);
        return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
    }

    /**
     * Pone la tarea como C O M P L E T A D A (lo mas importante del to-do list).
     * @param idTarea El ID de la Tarea que se termino.
     * @return La Tarea actualizada, ya completada.
     */
    @PutMapping("/{idTarea}/completar")
    public ResponseEntity<Tarea> completarTarea(@PathVariable Long idTarea) {
        Tarea tareaCompletada = tareaService.completarTarea(idTarea);
        return ResponseEntity.ok(tareaCompletada);
    }

    /**
     * Le da la vuelta, pone la tarea otra vez como PENDIENTE por si hubo algun error.
     */
    @PutMapping("/{idTarea}/pendiente")
    public ResponseEntity<Tarea> marcarPendiente(@PathVariable Long idTarea) {
        Tarea tareaPendiente = tareaService.marcarComoPendiente(idTarea);
        return ResponseEntity.ok(tareaPendiente);
    }
}