package com.ecocrop.smartcropwebservices.contenido_guias.infrastructure;

import com.ecocrop.smartcropwebservices.contenido_guias.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    // Obtener los comentarios más recientes (para el widget de "Comentarios Recientes")
    List<Comentario> findTop5ByOrderByFechaComentarioDesc();

    // Obtener comentarios de una Noticia específica
    List<Comentario> findByIdNoticiaOrderByFechaComentarioDesc(Long idNoticia);
}