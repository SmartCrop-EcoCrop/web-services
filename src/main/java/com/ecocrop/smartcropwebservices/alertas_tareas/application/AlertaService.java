package com.ecocrop.smartcropwebservices.alertas_tareas.application;

import com.ecocrop.smartcropwebservices.alertas_tareas.domain.Alerta;
import com.ecocrop.smartcropwebservices.alertas_tareas.domain.CondicionOptima;
import com.ecocrop.smartcropwebservices.alertas_tareas.domain.TipoAlerta;
import com.ecocrop.smartcropwebservices.alertas_tareas.infrastructure.AlertaRepository;
import com.ecocrop.smartcropwebservices.alertas_tareas.infrastructure.CondicionOptimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final CondicionOptimaRepository condicionOptimaRepository;
    // NOTA: En un proyecto real, se inyectaría un Servicio de Cultivos y un Servicio de Monitoreo

    @Autowired
    public AlertaService(AlertaRepository alertaRepository,
                         CondicionOptimaRepository condicionOptimaRepository) {
        this.alertaRepository = alertaRepository;
        this.condicionOptimaRepository = condicionOptimaRepository;
    }

    // READ: Obtiene las alertas activas para una Parcela (Dashboard)
    @Transactional(readOnly = true)
    public List<Alerta> obtenerAlertasActivasPorParcela(Long idParcela) {
        return alertaRepository.findByIdParcelaAndActiva(idParcela, true);
    }

    // READ: Obtiene el catálogo de reglas óptimas (para la interfaz de administración)
    @Transactional(readOnly = true)
    public List<CondicionOptima> obtenerReglasOptimas() {
        return condicionOptimaRepository.findAll();
    }

    // CORE DE LA LÓGICA DE NEGOCIO: Genera una alerta basada en datos y reglas.
    // Esto se llamaría desde un Cron Job o un Listener de datos IoT.
    @Transactional
    public void evaluarYGenerarAlerta(Long idParcela, String tipoCultivo, Double tempAireActual, Double humedadSueloActual) {

        // 1. Obtener la regla de negocio para el cultivo
        CondicionOptima regla = condicionOptimaRepository.findByTipoCultivo(tipoCultivo)
                .orElse(null);

        if (regla == null) {
            // No hay reglas definidas, no se puede evaluar.
            return;
        }

        // 2. Evaluar condiciones de RIESGO (Ej: Temperatura Alta)
        if (tempAireActual > regla.getTempMax()) {
            crearAlerta(idParcela,
                    TipoAlerta.TEMPERATURA_ALTA,
                    "Temperatura crítica de " + tempAireActual + "°C.");
        }

        // 3. Evaluar condiciones ÓPTIMAS (Ej: Siembra)
        if (tempAireActual >= regla.getTempMin() && tempAireActual <= regla.getTempMax() &&
                humedadSueloActual >= regla.getHumedadMin())
        {
            // Solo creamos la alerta si no existe una condición óptima activa para evitar duplicados
            if (alertaRepository.findByTipoAndIdParcela(TipoAlerta.CONDICION_OPTIMA, idParcela).isEmpty()) {
                crearAlerta(idParcela,
                        TipoAlerta.CONDICION_OPTIMA,
                        "Condiciones ideales para: " + regla.getTareaSugerida());
            }
        }
    }

    // Método auxiliar para guardar una alerta
    private Alerta crearAlerta(Long idParcela, TipoAlerta tipo, String mensaje) {
        Alerta alerta = new Alerta();
        alerta.setIdParcela(idParcela);
        alerta.setTipo(tipo);
        alerta.setMensaje(mensaje);
        alerta.setActiva(true);
        alerta.setFechaHoraInicio(LocalDateTime.now());
        alerta.setEstado("ACTIVA");
        // Aquí podrías agregar lógica para asociar la alerta a una tarea si aplica
        return alertaRepository.save(alerta);
    }
}