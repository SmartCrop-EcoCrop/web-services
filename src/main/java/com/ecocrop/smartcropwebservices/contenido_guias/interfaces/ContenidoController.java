package com.ecocrop.smartcropwebservices.contenido_guias.interfaces;

import com.ecocrop.smartcropwebservices.contenido_guias.application.ContenidoService;
import com.ecocrop.smartcropwebservices.contenido_guias.domain.Comentario;
import com.ecocrop.smartcropwebservices.contenido_guias.domain.GuiaPractica;
import com.ecocrop.smartcropwebservices.contenido_guias.domain.Noticia;
import com.ecocrop.smartcropwebservices.contenido_guias.infrastructure.GuiaPracticaRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contenido")
@Tag(name = "Contenido") // Etiqueta Swagger limpia
public class ContenidoController {

    private final ContenidoService contenidoService;

    @Autowired
    public ContenidoController(ContenidoService contenidoService) {
        this.contenidoService = contenidoService;
    }

    // Noticias
    @GetMapping("/noticias/recientes")
    public ResponseEntity<List<Noticia>> obtenerNoticiasRecientes() {
        List<Noticia> noticias = contenidoService.obtenerUltimasNoticias();
        return ResponseEntity.ok(noticias);
    }

    // Guías
    @GetMapping("/guias")
    public ResponseEntity<List<GuiaPractica>> obtenerGuias(
            @RequestParam(required = false) String cultivo) {

        List<GuiaPractica> guias;
        if (cultivo != null) {
            // Lógica con filtro
            guias = contenidoService.obtenerGuiasPorCultivo(cultivo);
        } else {
            // Lógica sin filtro: delegamos al Servicio
            guias = contenidoService.obtenerTodasLasGuias();
        }
        return ResponseEntity.ok(guias);
    }

    // Comentarios
    @GetMapping("/comentarios/recientes")
    public ResponseEntity<List<Comentario>> obtenerComentariosRecientes() {
        List<Comentario> comentarios = contenidoService.obtenerComentariosRecientes();
        return ResponseEntity.ok(comentarios);
    }

    @PostMapping("/comentar")
    public ResponseEntity<Comentario> crearComentario(@RequestBody Comentario comentario) {
        Comentario nuevoComentario = contenidoService.crearComentario(comentario);
        return new ResponseEntity<>(nuevoComentario, HttpStatus.CREATED);
    }

    @PostMapping("/noticias")
    public ResponseEntity<Noticia> publicarNoticia(@RequestBody Noticia noticia) {
        Noticia noticiaPublicada = contenidoService.publicarNoticia(noticia);
        return new ResponseEntity<>(noticiaPublicada, HttpStatus.CREATED);
    }
}