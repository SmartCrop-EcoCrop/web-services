package com.ecocrop.smartcropwebservices.cultivos.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor; // Añadido
import lombok.Getter;
import lombok.NoArgsConstructor; // ¡CRUCIAL! Añadido
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor // Requerido por JPA/Hibernate para instanciar la entidad
@AllArgsConstructor // Útil para crear objetos de dominio rápidamente
public class Finca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFinca;

    private Long idUsuario;
    private String nombreFinca;

    // Mapeo del Value Object a columnas específicas
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitud", column = @Column(name = "latitud")),
            @AttributeOverride(name = "longitud", column = @Column(name = "longitud"))
    })
    private UbicacionGeo ubicacionGeo;

    // Relación de Parcela dentro del agregado Finca
    @OneToMany(mappedBy = "finca", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Lado Padre: Permite serializar Parcelas
    private List<Parcela> parcelas;

    // Método de dominio: Cambiar el nombre (Controla la lógica del cambio)
    public void actualizarNombre(String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío.");
        }
        this.nombreFinca = nuevoNombre;
    }
}