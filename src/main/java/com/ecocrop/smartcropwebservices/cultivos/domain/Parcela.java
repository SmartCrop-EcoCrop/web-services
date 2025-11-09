package com.ecocrop.smartcropwebservices.cultivos.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor; // ¡CRUCIAL!
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor // Requerido
@AllArgsConstructor
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParcela;

    private String nombreParcela;
    private String tipoCultivo;
    private Double tamanoHa;

    // Relación a Finca
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_finca", nullable = false)
    @JsonBackReference
    private Finca finca;
}