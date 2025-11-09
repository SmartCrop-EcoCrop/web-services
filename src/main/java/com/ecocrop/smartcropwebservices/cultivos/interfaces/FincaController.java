package com.ecocrop.smartcropwebservices.cultivos.interfaces;

import com.ecocrop.smartcropwebservices.cultivos.application.FincaService;
import com.ecocrop.smartcropwebservices.cultivos.domain.Finca;
import com.ecocrop.smartcropwebservices.cultivos.interfaces.dto.FincaRequest;
import jakarta.validation.Valid; // Importante para la validación
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecocrop.smartcropwebservices.cultivos.interfaces.dto.FincaUpdateRequest;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fincas")
@Tag(name="Finca")
public class FincaController {

    private final FincaService fincaService;

    @Autowired
    public FincaController(FincaService fincaService) {
        this.fincaService = fincaService;
    }

    // POST: Crear una Finca usando un DTO y validación
    @PostMapping
    public ResponseEntity<Finca> crearFinca(@Valid @RequestBody FincaRequest request) {
        Finca nuevaFinca = fincaService.crearFincaDesdeDto(request);
        return new ResponseEntity<>(nuevaFinca, HttpStatus.CREATED);
    }

    // GET: Obtener todas las Fincas
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Finca>> obtenerFincas(@PathVariable Long idUsuario) {
        List<Finca> fincas = fincaService.obtenerFincasPorUsuario(idUsuario);
        return ResponseEntity.ok(fincas);
    }
    // GET: Obtener una Finca por ID
    @GetMapping("/{idFinca}") //
    public ResponseEntity<Finca> obtenerFinca(@PathVariable Long idFinca) {
        Finca finca = fincaService.obtenerFincaPorId(idFinca);
        return ResponseEntity.ok(finca);
    }


    // PUT: Actualizar una Finca existente
    @PutMapping("/{idFinca}")
    public ResponseEntity<Finca> actualizarFinca(@PathVariable Long idFinca,
                                                 @Valid @RequestBody FincaUpdateRequest request) {
        Finca fincaActualizada = fincaService.actualizarFinca(idFinca, request);
        return ResponseEntity.ok(fincaActualizada);
    }

    // DELETE: Eliminar una Finca
    @DeleteMapping("/{idFinca}")
    public ResponseEntity<Void> eliminarFinca(@PathVariable Long idFinca) {
        fincaService.eliminarFinca(idFinca);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
}