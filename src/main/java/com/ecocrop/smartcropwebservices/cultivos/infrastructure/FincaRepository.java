package com.ecocrop.smartcropwebservices.cultivos.infrastructure;

import com.ecocrop.smartcropwebservices.cultivos.domain.Finca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Importar List

@Repository
public interface FincaRepository extends JpaRepository<Finca, Long> {

    /**
     * Busca y retorna todas las Fincas asociadas a un ID de Usuario específico.
     */
    List<Finca> findByIdUsuario(Long idUsuario);

}