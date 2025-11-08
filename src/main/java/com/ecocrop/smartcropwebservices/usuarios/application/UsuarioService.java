package com.ecocrop.smartcropwebservices.usuarios.application;

import com.ecocrop.smartcropwebservices.usuarios.domain.Usuario;
import com.ecocrop.smartcropwebservices.usuarios.infrastructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    // private final PasswordEncoder passwordEncoder; // En un proyecto real, esto se inyecta

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository /*, PasswordEncoder passwordEncoder */) {
        this.usuarioRepository = usuarioRepository;
        // this.passwordEncoder = passwordEncoder;
    }

    // Lógica para registrar un nuevo usuario
    @Transactional
    public Usuario registrarUsuario(Usuario nuevoUsuario) {
        if (usuarioRepository.existsByEmail(nuevoUsuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado: " + nuevoUsuario.getEmail());
        }

        // **IMPORTANTE:** Aquí se debería CIFRAR la contraseña antes de guardar.
        // nuevoUsuario.setPasswordHash(passwordEncoder.encode(nuevoUsuario.getPasswordHash()));

        // Por simplicidad, por ahora guardamos la contraseña sin cifrar para pruebas.

        return usuarioRepository.save(nuevoUsuario);
    }

    // Lógica para obtener el perfil del usuario (usado por el dashboard)
    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Lógica usada por Spring Security para el login
    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> validarCredenciales(String email, String password) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // ⚠️ ADVERTENCIA DE SEGURIDAD ⚠️
            // En producción, NUNCA compares contraseñas así.
            // Usa un PasswordEncoder para comparar el password plano con el hash.
            // if (passwordEncoder.matches(password, usuario.getPasswordHash())) { ... }

            if (usuario.getPasswordHash().equals(password)) {
                return Optional.of(usuario); // Credenciales válidas
            }
        }

        return Optional.empty(); // Falla si el email no existe o el password no coincide.
    }
}