package com.ecocrop.smartcropwebservices.monitoreo.infrastructure;

import com.ecocrop.smartcropwebservices.monitoreo.domain.DispositivoIot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DispositivoIotRepository extends JpaRepository<DispositivoIot, String> {
    // Buscar dispositivos por Parcela para ver qué datos leer
    List<DispositivoIot> findByIdParcela(Long idParcela);
}