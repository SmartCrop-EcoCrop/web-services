package com.ecocrop.smartcropwebservices.usuarios.infrastructure;

import com.ecocrop.smartcropwebservices.usuarios.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Consulta crítica para la autenticación (Spring Security lo usará)
    Optional<Usuario> findByEmail(String email);

    // Comprobar si un email ya existe durante el registro
    boolean existsByEmail(String email);
}