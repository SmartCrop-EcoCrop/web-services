package com.ecocrop.smartcropwebservices.usuarios.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(unique = true)
    private String email;

    private String nombre;
    private String apellido;
    private String telefono;

    private String passwordHash;

    private String rol; // Ej: 'AGRICULTOR', 'ADMIN', 'EDITOR'
    private Boolean activo = true;
}