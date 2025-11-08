package com.ecocrop.smartcropwebservices.monitoreo.application;

import com.ecocrop.smartcropwebservices.alertas_tareas.application.AlertaService;
import com.ecocrop.smartcropwebservices.cultivos.application.FincaService;
import com.ecocrop.smartcropwebservices.cultivos.domain.Parcela;
import com.ecocrop.smartcropwebservices.monitoreo.domain.DispositivoIot; // NUEVO: Se necesita para la inyección
import com.ecocrop.smartcropwebservices.monitoreo.domain.RegistroDato;
import com.ecocrop.smartcropwebservices.monitoreo.infrastructure.DispositivoIotRepository; // NUEVO
import com.ecocrop.smartcropwebservices.monitoreo.infrastructure.RegistroDatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MonitoreoService {

    private final RegistroDatoRepository registroDatoRepository;
    private final DispositivoIotRepository dispositivoIotRepository; // <-- Agregado para buscar el ID de Parcela
    private final AlertaService alertaService;
    private final FincaService fincaService;

    @Autowired
    public MonitoreoService(RegistroDatoRepository registroDatoRepository,
                            DispositivoIotRepository dispositivoIotRepository, // <-- Inyectar
                            AlertaService alertaService,
                            FincaService fincaService) {
        this.registroDatoRepository = registroDatoRepository;
        this.dispositivoIotRepository = dispositivoIotRepository; // <-- Asignar
        this.alertaService = alertaService;
        this.fincaService = fincaService;
    }

    // 🌟 CORE: Recibe el dato, lo almacena y evalúa el riesgo/oportunidad
    @Transactional
    public RegistroDato recibirYProcesarDato(RegistroDato nuevoDato) {

        // 1. Almacenar el dato (RegistroDato)
        RegistroDato savedDato = registroDatoRepository.save(nuevoDato);

        // 2. Obtener la Parcela ID a través del Dispositivo IoT (Integración DDD)
        String idDispositivo = savedDato.getIdDispositivo(); // El dato siempre tiene el ID del dispositivo

        // Buscar el Dispositivo IoT para obtener la Parcela ID
        DispositivoIot dispositivo = dispositivoIotRepository.findById(idDispositivo)
                .orElseThrow(() -> new RuntimeException("Dispositivo IoT no encontrado: " + idDispositivo));

        Long idParcela = dispositivo.getIdParcela(); // ✅ Obtenemos el ID de Parcela

        // 3. INTEGRACIÓN DDD: Obtener el Tipo de Cultivo
        Parcela parcela = fincaService.obtenerParcelaPorId(idParcela);
        String tipoCultivo = parcela.getTipoCultivo();

        // 4. Llamar al servicio del Contexto de Alertas para evaluar
        alertaService.evaluarYGenerarAlerta(
                idParcela,
                tipoCultivo,
                savedDato.getTemperaturaAireCelsius(),
                savedDato.getHumedadSueloPorcentaje()
        );

        return savedDato;
    }

    // READ: Obtener historial de datos (para gráficas)
    @Transactional(readOnly = true)
    public List<RegistroDato> obtenerHistorial(String idDispositivo, LocalDateTime inicio, LocalDateTime fin) {
        return registroDatoRepository.findByIdDispositivoAndFechaHoraBetweenOrderByFechaHoraAsc(idDispositivo, inicio, fin);
    }
}