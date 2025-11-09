package com.ecocrop.smartcropwebservices.usuarios.interfaces;

import com.ecocrop.smartcropwebservices.usuarios.application.UsuarioService;
import com.ecocrop.smartcropwebservices.usuarios.domain.Usuario;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import com.ecocrop.smartcropwebservices.usuarios.interfaces.dto.LoginRequest; // ¡Importar DTO!

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * POST: Endpoint público para el registro de nuevos usuarios.
     */
    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    /**
     * GET: Obtener el perfil de un usuario por ID.
     */
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
        return ResponseEntity.ok(usuario);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        Optional<Usuario> usuario = usuarioService.validarCredenciales(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        if (usuario.isPresent()) {
            // ÉXITO: Autenticación exitosa.
            return ResponseEntity.ok(
                    new LoginResponse("Autenticación exitosa. Bienvenido, " + usuario.get().getNombre(), usuario.get().getIdUsuario())
            );
        } else {
            // FALLO: Credenciales inválidas.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas (email o contraseña incorrectos).");
        }
    }

    private static class LoginResponse {
        public String mensaje;
        public Long idUsuario;

        public LoginResponse(String mensaje, Long idUsuario) {
            this.mensaje = mensaje;
            this.idUsuario = idUsuario;
        }
    }

}