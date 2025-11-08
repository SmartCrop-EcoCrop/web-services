package com.ecocrop.smartcropwebservices.alertas_tareas.interfaces;

import com.ecocrop.smartcropwebservices.alertas_tareas.application.AlertaService;
import com.ecocrop.smartcropwebservices.alertas_tareas.domain.Alerta;
import com.ecocrop.smartcropwebservices.alertas_tareas.domain.CondicionOptima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alertas")
@Tag(name = "Alerta")
public class AlertaController {

    private final AlertaService alertaService;

    @Autowired
    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    /**
     * GET: Obtiene la lista de alertas activas para una parcela específica (para el dashboard).
     * @param idParcela ID de la Parcela.
     * @return Lista de Alertas activas.
     */
    @GetMapping("/parcela/{idParcela}/activas")
    public ResponseEntity<List<Alerta>> obtenerAlertasActivas(@PathVariable Long idParcela) {
        List<Alerta> alertas = alertaService.obtenerAlertasActivasPorParcela(idParcela);
        return ResponseEntity.ok(alertas);
    }

    /**
     * GET: Permite a los administradores ver las reglas que disparan las alertas.
     * @return Lista de reglas CondicionOptima.
     */
    @GetMapping("/reglas-optimas")
    public ResponseEntity<List<CondicionOptima>> obtenerReglasOptimas() {
        List<CondicionOptima> reglas = alertaService.obtenerReglasOptimas();
        return ResponseEntity.ok(reglas);
    }

    /**
     * POST: Endpoint de prueba para simular la evaluación de datos IoT
     * NOTA: Este endpoint solo es para prueba/desarrollo. En producción, la lógica se ejecuta en segundo plano.
     */
    @PostMapping("/evaluar-datos-prueba")
    public ResponseEntity<Void> evaluarDatosDePrueba(
            @RequestParam Long idParcela,
            @RequestParam String tipoCultivo,
            @RequestParam Double temp,
            @RequestParam Double humedad) {

        alertaService.evaluarYGenerarAlerta(idParcela, tipoCultivo, temp, humedad);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
}