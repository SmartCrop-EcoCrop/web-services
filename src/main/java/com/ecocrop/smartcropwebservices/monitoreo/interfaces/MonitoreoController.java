package com.ecocrop.smartcropwebservices.monitoreo.interfaces;

import com.ecocrop.smartcropwebservices.monitoreo.application.MonitoreoService;
import com.ecocrop.smartcropwebservices.monitoreo.domain.RegistroDato;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/monitoreo")
@Tag(name = "Monitoreo IoT")
public class MonitoreoController {

    private final MonitoreoService monitoreoService;

    @Autowired
    public MonitoreoController(MonitoreoService monitoreoService) {
        this.monitoreoService = monitoreoService;
    }

    /**
     * POST: Endpoint real para recibir datos de un dispositivo
     */
    @PostMapping("/ingresar")
    public ResponseEntity<RegistroDato> ingestarDato(@RequestBody RegistroDato registroDato) {

        RegistroDato savedDato = monitoreoService.recibirYProcesarDato(registroDato);
        return new ResponseEntity<>(savedDato, HttpStatus.CREATED);
    }

    /**
     * GET: Obtener el historial de datos para una grafica que no vamos a hacer xddd.
     */
    @GetMapping("/historial/{idDispositivo}")
    public ResponseEntity<List<RegistroDato>> obtenerHistorial(
            @PathVariable String idDispositivo,
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fin) {

        List<RegistroDato> historial = monitoreoService.obtenerHistorial(idDispositivo, inicio, fin);
        return ResponseEntity.ok(historial);
    }
}