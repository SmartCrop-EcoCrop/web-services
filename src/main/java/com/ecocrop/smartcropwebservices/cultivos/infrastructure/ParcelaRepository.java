package com.ecocrop.smartcropwebservices.cultivos.infrastructure;

import com.ecocrop.smartcropwebservices.cultivos.domain.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {
    // Este repositorio nos permite buscar directamente por ID de Parcela,
    // que es la clave para la integración con el contexto IoT/Alertas.
    Optional<Parcela> findById(Long idParcela);
}