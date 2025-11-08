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
    private String email; // Usado para login

    private String nombre;
    private String apellido;
    private String telefono;

    // Campo crítico para seguridad (Almacenar HASHED/Cifrado)
    private String passwordHash;

    private String rol; // Ej: 'AGRICULTOR', 'ADMIN', 'EDITOR'
    private Boolean activo = true;
}