package com.ecocrop.smartcropwebservices.contenido_guias.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "noticia")
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNoticia;

    private String titulo;
    private String resumen;
    @Lob
    private String contenidoCompleto;
    private LocalDateTime fechaPublicacion;
    private Integer tiempoLecturaMin;
    private String imagenPrincipalUrl;
    private String autor;
}