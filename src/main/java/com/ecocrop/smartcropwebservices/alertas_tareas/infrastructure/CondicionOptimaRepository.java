package com.ecocrop.smartcropwebservices.alertas_tareas.infrastructure;

import com.ecocrop.smartcropwebservices.alertas_tareas.domain.CondicionOptima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondicionOptimaRepository extends JpaRepository<CondicionOptima, Long> {

    // Función de búsqueda más crítica: Obtener la regla para un tipo de cultivo
    Optional<CondicionOptima> findByTipoCultivo(String tipoCultivo);
}