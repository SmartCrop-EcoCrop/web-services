package com.ecocrop.smartcropwebservices.cultivos.infrastructure;

import com.ecocrop.smartcropwebservices.cultivos.domain.Finca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Importar List

@Repository
public interface FincaRepository extends JpaRepository<Finca, Long> {

    // ¡ESTE MÉTODO ES EL QUE FALTA Y DEBE SER DECLARADO AQUÍ!
    /**
     * Busca y retorna todas las Fincas asociadas a un ID de Usuario específico.
     * Spring Data JPA lo implementa automáticamente.
     */
    List<Finca> findByIdUsuario(Long idUsuario);

    // Si usaste el código anterior, esta línea ya estaba:
    // List<Finca> findByIdUsuario(Long idUsuario);
}