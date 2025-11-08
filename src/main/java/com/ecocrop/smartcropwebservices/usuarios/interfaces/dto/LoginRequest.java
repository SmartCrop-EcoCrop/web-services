// usuarios/interfaces/dto/LoginRequest.java
package com.ecocrop.smartcropwebservices.usuarios.interfaces.dto;

import lombok.Getter;
import lombok.Setter;

// DTO para recibir el email y la contraseña del login.
@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;
}