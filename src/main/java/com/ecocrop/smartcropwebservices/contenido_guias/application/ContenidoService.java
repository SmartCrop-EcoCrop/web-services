package com.ecocrop.smartcropwebservices.contenido_guias.application;

import com.ecocrop.smartcropwebservices.contenido_guias.domain.Comentario;
import com.ecocrop.smartcropwebservices.contenido_guias.domain.GuiaPractica;
import com.ecocrop.smartcropwebservices.contenido_guias.domain.Noticia;
import com.ecocrop.smartcropwebservices.contenido_guias.infrastructure.ComentarioRepository;
import com.ecocrop.smartcropwebservices.contenido_guias.infrastructure.GuiaPracticaRepository;
import com.ecocrop.smartcropwebservices.contenido_guias.infrastructure.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContenidoService {

    private final NoticiaRepository noticiaRepository;
    private final GuiaPracticaRepository guiaPracticaRepository;
    private final ComentarioRepository comentarioRepository;

    @Autowired
    public ContenidoService(NoticiaRepository noticiaRepository,
                            GuiaPracticaRepository guiaPracticaRepository,
                            ComentarioRepository comentarioRepository) {
        this.noticiaRepository = noticiaRepository;
        this.guiaPracticaRepository = guiaPracticaRepository;
        this.comentarioRepository = comentarioRepository;
    }

    // Funcionalidades de Noticias
    @Transactional(readOnly = true)
    public List<Noticia> obtenerUltimasNoticias() {
        return noticiaRepository.findTop5ByOrderByFechaPublicacionDesc();
    }


    @Transactional(readOnly = true)
    public List<GuiaPractica> obtenerTodasLasGuias() {
        return guiaPracticaRepository.findAll();
    }

    // Funcionalidades de Guías
    @Transactional(readOnly = true)
    public List<GuiaPractica> obtenerGuiasPorCultivo(String cultivo) {
        return guiaPracticaRepository.findByCultivoAplicable(cultivo);
    }

    // Funcionalidades de Comentarios
    @Transactional(readOnly = true)
    public List<Comentario> obtenerComentariosRecientes() {
        return comentarioRepository.findTop5ByOrderByFechaComentarioDesc();
    }

    // Publicación de Contenido (CRUD simple para el panel de administración)
    @Transactional
    public Noticia publicarNoticia(Noticia noticia) {
        // Lógica de validación de publicación, fecha, etc.
        return noticiaRepository.save(noticia);
    }

    // Crear Comentario
    @Transactional
    public Comentario crearComentario(Comentario comentario) {
        // Lógica de validación
        return comentarioRepository.save(comentario);
    }


}