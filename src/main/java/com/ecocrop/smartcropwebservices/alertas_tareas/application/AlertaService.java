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
    // Ojo: aqui faltaria inyectar servicios de Cultivos y Monitoreo pero meh despues lo veo

    @Autowired
    public AlertaService(AlertaRepository alertaRepository,
                         CondicionOptimaRepository condicionOptimaRepository) {
        this.alertaRepository = alertaRepository;
        this.condicionOptimaRepository = condicionOptimaRepository;
    }

    // Trae las alertas activas para una Parcela (lo que ve el usuario)
    @Transactional(readOnly = true)
    public List<Alerta> obtenerAlertasActivasPorParcela(Long idParcela) {
        return alertaRepository.findByIdParcelaAndActiva(idParcela, true);
    }

    // Trae todas las reglas de negocio (pa la interfaz de admin)
    @Transactional(readOnly = true)
    public List<CondicionOptima> obtenerReglasOptimas() {
        return condicionOptimaRepository.findAll();
    }

    // Esta es la logica principal: Chequea datos y crea la alerta si es necesario.
    // Esto lo va a llamar un cron o algo automatico cuando entre info nueva.
    @Transactional
    public void evaluarYGenerarAlerta(Long idParcela, String tipoCultivo, Double tempAireActual, Double humedadSueloActual) {

        // 1. Buscamos la regla que aplica a ese cultivo
        CondicionOptima regla = condicionOptimaRepository.findByTipoCultivo(tipoCultivo)
                .orElse(null);

        if (regla == null) {
            // No hay regla definida, asi q no hacemos nada. Sale.
            return;
        }

        // 2. Revisamos si hay algun RIESGO (por ejemplo, si la temperatura es muy alta)
        if (tempAireActual > regla.getTempMax()) {
            crearAlerta(idParcela,
                    TipoAlerta.TEMPERATURA_ALTA,
                    "Temperatura critica de " + tempAireActual + "°C.");
        }

        // 3. Revisamos si las condiciones son IDEALES (por ejemplo, para sembrar)
        if (tempAireActual >= regla.getTempMin() && tempAireActual <= regla.getTempMax() &&
                humedadSueloActual >= regla.getHumedadMin())
        {
            // Solo creamos la alerta si no existe ya una de "condicion optima" para que no se repita
            if (alertaRepository.findByTipoAndIdParcela(TipoAlerta.CONDICION_OPTIMA, idParcela).isEmpty()) {
                crearAlerta(idParcela,
                        TipoAlerta.CONDICION_OPTIMA,
                        "Condiciones ideales para: " + regla.getTareaSugerida());
            }
        }
    }

    // Metodo privado para guardar la alerta rapido
    private Alerta crearAlerta(Long idParcela, TipoAlerta tipo, String mensaje) {
        Alerta alerta = new Alerta();
        alerta.setIdParcela(idParcela);
        alerta.setTipo(tipo);
        alerta.setMensaje(mensaje);
        alerta.setActiva(true);
        alerta.setFechaHoraInicio(LocalDateTime.now());
        alerta.setEstado("ACTIVA");
        // Habria que ver si esto necesita asociarse a una tarea mas adelante
        return alertaRepository.save(alerta);
    }
}