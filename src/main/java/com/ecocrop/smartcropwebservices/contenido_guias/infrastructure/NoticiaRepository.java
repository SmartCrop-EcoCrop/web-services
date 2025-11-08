package com.ecocrop.smartcropwebservices.contenido_guias.infrastructure;

import com.ecocrop.smartcropwebservices.contenido_guias.domain.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    // Obtener las noticias más recientes para la página principal
    List<Noticia> findTop5ByOrderByFechaPublicacionDesc();
}