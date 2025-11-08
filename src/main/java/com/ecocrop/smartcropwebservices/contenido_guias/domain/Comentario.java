package com.ecocrop.smartcropwebservices.contenido_guias.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComentario;

    // Referencias al contenido (Uno debe ser NULL, el otro no)
    private Long idNoticia;
    private Long idGuia;

    // Referencia al usuario (del Contexto de Usuarios)
    private Long idUsuario;

    @Lob
    private String textoComentario;
    private LocalDateTime fechaComentario = LocalDateTime.now();
    private String noticiaComentadaTitulo; // Para referencia rápida en listas
}