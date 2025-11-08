package com.ecocrop.smartcropwebservices.contenido_guias.infrastructure;

import com.ecocrop.smartcropwebservices.contenido_guias.domain.GuiaPractica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuiaPracticaRepository extends JpaRepository<GuiaPractica, Long> {
    // Buscar guías por tipo de cultivo (para el filtro de la aplicación)
    List<GuiaPractica> findByCultivoAplicable(String cultivoAplicable);
}