package com.ecocrop.smartcropwebservices.contenido_guias.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "guia_practica")
public class GuiaPractica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGuia;

    private String titulo;
    @Lob
    private String contenidoHtml;
    private String categoria;
    private String cultivoAplicable;
}